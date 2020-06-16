import adapters.LocalDateDeserializer;
import adapters.LocalDateSerializer;
import adapters.LoggerSetup;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import exchange.Request;
import exchange.Response;
import runner.Executor;
import ticket.Ticket;
import ticket.TicketStorage;

import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private final int port;
    private Gson gson;
    private ByteBuffer buffer;
    private boolean running;
    private Executor executor;
    private DatagramChannel channel;
    private Logger logger;
    public Server(int port) throws IOException {
        gson =
                new GsonBuilder()
                        .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                        .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                        .create();
        buffer = ByteBuffer.allocate(8192);
        running = true;
        channel = DatagramChannel.open();
        channel.bind(new InetSocketAddress(port));
        this.port = port;
        this.executor = new Executor();
        logger = Logger.getLogger(Server.class.getName());
        LoggerSetup.setupLogger(logger, "server" + File.separator + LocalDate.now() + "_log.log");
    }
    public Executor getExecutor() {
        return executor;
    }
    public boolean isRunning() {
        return running;
    }
    public void close() {
        try {
            channel.socket().disconnect();
            channel.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка закрытия соединения.", e);
        }
    }
    public void process() {
        try {
            buffer.clear();
            Request request;
            DatagramPacket receivePacket = new DatagramPacket(buffer.array(), buffer.remaining());
            logger.fine("Ожидание команды от клиента");
            channel.socket().receive(receivePacket);
            String data = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("data: " + data);
            request = gson.fromJson(data, Request.class);
            request.setCollection(executor.getEditor().getCollection());
            logger.info(
                    "Данные клиента: "
                            + System.lineSeparator()
                            + "Имя комманды: "
                            + request.getCommandName()
                            + System.lineSeparator()
                            + "Параметр: "
                            + request.getCommandParameter()
                            + System.lineSeparator()
                            + "Билет: "
                            + request.getTicket());
            if (request.getTicket() != null){
                Ticket t = TicketStorage.decriptTicket(data.replaceAll(":", "="));                                // chabged rquest.getTicket to Ticketstorahge
                System.out.println("SERVER >>> "+t.getType());
                try {
                    TicketStorage.putTicket(t);
                }catch (NullPointerException e){
                    //
                }
            }
            buffer.clear();
            Response response = executor.executeCommand(request);
            data = gson.toJson(response);
            try {
                buffer.put(data.getBytes());
            } catch (BufferOverflowException e) {
                buffer.put(
                        gson.toJson(new Response(response.isCorrect(), "Ответ от сервера слишком большой."))
                                .getBytes());
            }
            buffer.flip();
            DatagramPacket sendPacket =
                    new DatagramPacket(
                            buffer.array(),
                            buffer.remaining(),
                            receivePacket.getAddress(),
                            receivePacket.getPort());
            channel.socket().send(sendPacket);
        } catch (IOException | JsonSyntaxException e) {
            logger.severe("Ошибка. Закрытие соединения.");
            close();
        }
    }
    public boolean isConnected() {
        return !channel.socket().isClosed();
    }
}