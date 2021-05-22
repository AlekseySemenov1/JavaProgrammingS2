package ServerPath;

import Command.*;
import Messages.Response;
import Other.CollectionManager;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.ArrayList;

public class CommandReader {
    private CollectionManager manager;
    private ArrayList<Command> commandsList;
    private ResponseWriter resWriter;

    public CommandReader(CollectionManager manager, ArrayList<Command> commandsList, ResponseWriter resWriter) {
        this.manager = manager;
        this.commandsList = commandsList;
        this.resWriter = resWriter;
    }

    public void executeCommand(Command com, Object args, SocketAddress address) throws IOException {
        String message;
        switch (com.getName()) {
            case "add":
                message = commandsList.get(0).execute(args, manager);
                resWriter.writeResponse(new Response(message), address);
                ((ComHistory) commandsList.get(7)).addToHistory(com.getName());
                break;

            case "clear":
                message = commandsList.get(1).execute(args,manager);
                resWriter.writeResponse(new Response(message), address);
                ((ComHistory) commandsList.get(7)).addToHistory(com.getName());
                break;

            case "count_less_than_discipline":
                message = commandsList.get(2).execute(args, manager);
                resWriter.writeResponse(new Response(message), address);
                ((ComHistory) commandsList.get(7)).addToHistory(com.getName());
                break;

            case "print_field_descending_difficulty":
                message = commandsList.get(5).execute(args, manager);
                resWriter.writeResponse(new Response(message), address);
                ((ComHistory) commandsList.get(7)).addToHistory(com.getName());
                break;

            case "filter_by_difficulty":
                message = commandsList.get(3).execute(args, manager);
                resWriter.writeResponse(new Response(message), address);
                ((ComHistory) commandsList.get(7)).addToHistory(com.getName());
                break;

            case "help":
                message = commandsList.stream().reduce("", (names, c) -> names += c.getName() + ' ' + c.getDescription() + "\n", (names, c)-> names + c).trim();
                resWriter.writeResponse(new Response(message), address);
                ((ComHistory) commandsList.get(7)).addToHistory(com.getName());
                break;

            case "history":
                message = commandsList.get(7).execute(args, manager);
                resWriter.writeResponse(new Response(message), address);
                ((ComHistory) commandsList.get(7)).addToHistory(com.getName());
                break;

            case "info":
                message = commandsList.get(8).execute(args, manager);
                resWriter.writeResponse(new Response(message), address);
                ((ComHistory) commandsList.get(7)).addToHistory(com.getName());
                break;

            case "remove_by_id":
                message = commandsList.get(9).execute(args, manager);
                resWriter.writeResponse(new Response(message), address);
                ((ComHistory) commandsList.get(7)).addToHistory(com.getName());
                break;

            case "remove_head":
                message = commandsList.get(10).execute(args, manager);
                resWriter.writeResponse(new Response(message), address);
                ((ComHistory) commandsList.get(7)).addToHistory(com.getName());
                break;

            case "remove_lower":
                message = commandsList.get(11).execute(args, manager);
                resWriter.writeResponse(new Response(message), address);
                ((ComHistory) commandsList.get(7)).addToHistory(com.getName());
                break;

            case "execute_script":
                message = commandsList.get(12).execute(args, manager);
                resWriter.writeResponse(new Response(message), address);
                ((ComHistory) commandsList.get(7)).addToHistory(com.getName());
                break;

            case "show":
                message = commandsList.get(13).execute(args, manager);
                resWriter.writeResponse(new Response(message), address);
                ((ComHistory) commandsList.get(7)).addToHistory(com.getName());
                break;

            case "update_by_id":
                message = commandsList.get(14).execute(args, manager);
                resWriter.writeResponse(new Response(message), address);
                ((ComHistory) commandsList.get(7)).addToHistory(com.getName());
                break;

            case "exit":
                message = commandsList.get(4).execute(args,manager);
                resWriter.writeResponse(new Response(message), address);
                ((ComHistory) commandsList.get(7)).addToHistory(com.getName());
                break;

        }
    }
}
