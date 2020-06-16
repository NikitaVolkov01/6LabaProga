package command.commands;

import command.Command;
import exchange.Request;
import exchange.Response;
import ticket.Ticket;
import runner.Editor;
import runner.ExecutorException;
import ticket.TicketStorage;

import javax.xml.bind.ValidationException;
import java.time.LocalDate;

public final class AddCommand implements Command {
    private Ticket ticket;
    public void addRequest(Request request){
        //System.out.println(request.getCommandName());
        System.out.println("ADDTENTION: >>> " + request.getTicket().toString());
       // String value = request.getTicket();
        this.ticket = request.getTicket();
    }
    @Override
    public Response execute(Editor editor) throws ExecutorException {
        //System.out.println("valuuuuue"+editor.());
        /*if (editor.getValue() == null) {
            throw new ExecutorException("Не введен ключ.");
        }
        int key;
        try {
            key = Integer.parseInt(editor.getValue());
        } catch (NumberFormatException e) {
            throw new ExecutorException("Неверный формат ключа.", e);
        }
        if (editor.getCollection().contains(key)) {
            return new Response(false, "Элемент с данным ключом уже существует.");
        }*/
        /*Ticket ticket;
        try {
            ticket = editor.getTicket();
        } catch (ValidationException e) {
            throw new ExecutorException(e);
        }
        if (ticket == null) {
            return new Response(true);
        }
        System.out.println(ticket);
        editor.getCollection().add(ticket);*/
        //Ticket ticket = TicketStorage.decriptTicket(value);
        try {
            //System.out.println("ATTENTION from ADDCOMMAND >>> " + ticket.getType());
            //ticket.setId(TicketStorage.getTickets().size() + 1);
            //ticket.setCreationDate(LocalDate.now());
        }catch (Exception e){
            //throw new ExecutorException();
        }

        //TicketStorage.putTicket(ticket);
        return new Response(true, "Добавление нового элемента прошло успешно.");
    }
}