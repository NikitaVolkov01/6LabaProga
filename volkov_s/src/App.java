import java.net.BindException;
import java.util.Scanner;

public class App {
    /**
     * Starts server
     *
     * @param args name of the file with data
     */
    public static void main(String[] args) {
        try {
            System.out.print("Введите порт ");
            Scanner sc = new Scanner(System.in);
            String port = sc.nextLine();
            Server server = new Server(Integer.parseInt(port));
            System.out.print("Введите имя файла ");
            String[] arg = new String[1];
            arg[0] = sc.nextLine();
            if (server.getExecutor().setArgs(arg)) {
                System.out.println(server.getExecutor().getEditor().load().getString());
                System.out.println("Сервер успешно запущен.");
                while (server.isRunning()) {
                    if (server.isConnected()) {
                        server.process();
                    }
                }
                server.close();
            }
        }catch (BindException e) {
            System.err.println("Порт уже занят");
        }catch (Throwable e) {
            //e.printStackTrace();
            System.err.println("Фатальная ошибка при работе сервера: " + e.getMessage());
        }
    }
}