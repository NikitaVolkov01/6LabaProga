package exchange;

import io.Input;
import io.Output;
import ticket.Ticket;
import ticket.TicketCreator;

import java.io.Serializable;
import java.util.ArrayList;

public class Request implements Serializable {
    private String commandName;
    private Ticket ticket;
    private String commandParameter;
    private ArrayList<Ticket> collection;
    public Request(String command) {
        if (command == null) {
            commandName = null;
            commandParameter = null;
        } else {
            System.out.println("REQUEST >>>> " + command);
            String[] values = command.split(" ", 2);
            commandName = values[0];
            if (values.length == 2) commandParameter = values[1];

            if (commandName.equals("add")
                    || commandName.equals("update_id")
                    || commandName.equals("replace_if_lower")) {
                ticket = new TicketCreator(new Input(), new Output(), collection).create();
            }
        }
    }
    public String getCommandName() {
        return commandName;
    }
    public String getCommandParameter() {
        return commandParameter;
    }
    public Ticket getTicket() {
        return ticket;
    }
    public void setCollection(ArrayList<Ticket> collection){
        this.collection = collection;
    }
}