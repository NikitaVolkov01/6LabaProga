package command.commands;


import command.Command;
import exchange.Response;
import runner.Editor;
import ticket.TicketStorage;

public final class ClearCommand implements Command {
    @Override
    public Response execute(Editor editor) {
        if (TicketStorage.getTickets().size() > 0) {
            TicketStorage.getTickets().clear();
            return new Response(true, "Коллекция очищена");
        }
        return new Response(false, "В коллекции нет элементов");
    }
}