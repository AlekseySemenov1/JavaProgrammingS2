package ServerPath;

import CollectionElements.LabWork;
import CollectionElements.User;
import Command.*;
import Messages.Response;
import Other.CollectionManager;

import java.io.IOException;
import java.net.SocketAddress;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CommandReader implements Runnable {
    private CollectionManager manager;
    private ArrayList<Command> commandsList;
    private ResponseWriter resWriter;
    private DataBaseConnector connector;
    private Command com;
    private Object args;
    private SocketAddress address;
    private User user;

    public CommandReader(CollectionManager manager, ArrayList<Command> commandsList, ResponseWriter resWriter, DataBaseConnector connector) {
        this.manager = manager;
        this.commandsList = commandsList;
        this.resWriter = resWriter;
        this.connector = connector;
    }

    @Override
    public void run() {
        try {
            executeCommand();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void executeCommand() throws IOException {
        try {
            String message;
            switch (com.getName()) {
                case "add":
                    LabWork laba = (LabWork) args;
                    laba.setId(connector.getNewId());
                    connector.addNewElementToBase(laba);
                    message = commandsList.get(0).execute(laba, manager, user);
                    resWriter.writeResponse(new Response(message, 1), address);
                    ((ComHistory) commandsList.get(7)).addToHistory(com.getName());
                    break;

                case "clear":
                    connector.deleteAllUserElement(user.getLogin());
                    message = commandsList.get(1).execute(args, manager, user);
                    resWriter.writeResponse(new Response(message, 1), address);
                    ((ComHistory) commandsList.get(7)).addToHistory(com.getName());
                    break;

                case "count_less_than_discipline":
                    message = commandsList.get(2).execute(args, manager, user);
                    resWriter.writeResponse(new Response(message, 1), address);
                    ((ComHistory) commandsList.get(7)).addToHistory(com.getName());
                    break;

                case "print_field_descending_difficulty":
                    message = commandsList.get(5).execute(args, manager, user);
                    resWriter.writeResponse(new Response(message, 1), address);
                    ((ComHistory) commandsList.get(7)).addToHistory(com.getName());
                    break;

                case "filter_by_difficulty":
                    message = commandsList.get(3).execute(args, manager, user);
                    resWriter.writeResponse(new Response(message, 1), address);
                    ((ComHistory) commandsList.get(7)).addToHistory(com.getName());
                    break;

                case "help":
                    message = commandsList.stream().reduce("", (names, c) -> names += c.getName() + ' ' + c.getDescription() + "\n", (names, c) -> names + c).trim();
                    resWriter.writeResponse(new Response(message, 1), address);
                    ((ComHistory) commandsList.get(7)).addToHistory(com.getName());
                    break;

                case "history":
                    message = commandsList.get(7).execute(args, manager, user);
                    resWriter.writeResponse(new Response(message, 1), address);
                    ((ComHistory) commandsList.get(7)).addToHistory(com.getName());
                    break;

                case "info":
                    message = commandsList.get(8).execute(args, manager, user);
                    resWriter.writeResponse(new Response(message, 1), address);
                    ((ComHistory) commandsList.get(7)).addToHistory(com.getName());
                    break;

                case "remove_by_id":
                    connector.deleteElementById(Long.parseLong((String) args), user.getLogin());
                    message = commandsList.get(9).execute(args, manager, user);
                    resWriter.writeResponse(new Response(message, ((ComRemoveById) commandsList.get(9)).getExecuteStatus()), address);
                    ((ComHistory) commandsList.get(7)).addToHistory(com.getName());
                    break;

                case "remove_head":
                    LabWork laba1 = ((ComRemoveHead) commandsList.get(10)).findElement(manager);
                    connector.deleteElementById(laba1.getId(), user.getLogin());
                    message = commandsList.get(10).execute(laba1, manager, user);
                    resWriter.writeResponse(new Response(message, ((ComRemoveHead) commandsList.get(10)).getExecuteStatus()), address);
                    ((ComHistory) commandsList.get(7)).addToHistory(com.getName());
                    break;

                case "remove_lower":
                    connector.deleteAllElementLowerThan(((LabWork) args).getId(), user.getLogin());
                    message = commandsList.get(11).execute(args, manager, user);
                    resWriter.writeResponse(new Response(message, 1), address);
                    ((ComHistory) commandsList.get(7)).addToHistory(com.getName());
                    break;

                case "execute_script":
                    message = commandsList.get(12).execute(args, manager, user);
                    resWriter.writeResponse(new Response(message, 1), address);
                    ((ComHistory) commandsList.get(7)).addToHistory(com.getName());
                    break;

                case "show":
                    message = commandsList.get(13).execute(args, manager, user);
                    resWriter.writeResponse(new Response(message, 1), address);
                    ((ComHistory) commandsList.get(7)).addToHistory(com.getName());
                    break;

                case "update_by_id":
                    if (args instanceof String) {
                        message = ((ComUpdateById) commandsList.get(14)).checkElement((String) args, manager, user);
                        resWriter.writeResponse(new Response(message, ((ComUpdateById) commandsList.get(14)).getExecuteStatus()), address);
                        ((ComHistory) commandsList.get(7)).addToHistory(com.getName());
                    } else if (args instanceof LabWork) {
                        connector.deleteElementById(((LabWork) args).getId(), user.getLogin());
                        connector.addNewElementToBase((LabWork) args);
                        message = commandsList.get(14).execute(args, manager, user);
                        resWriter.writeResponse(new Response(message, ((ComUpdateById) commandsList.get(14)).getExecuteStatus()), address);
                    }
                    break;

                case "exit":
                    message = commandsList.get(4).execute(args, manager, user);
                    resWriter.writeResponse(new Response(message, 1), address);
                    ((ComHistory) commandsList.get(7)).addToHistory(com.getName());
                    break;
            }
        } catch (SQLException e) {
            System.out.println("Произошла ошибка в бд, не удалось выполнить команду");
            e.printStackTrace();
        }
    }

    public void setCom(Command com) {
        this.com = com;
    }

    public void setArgs(Object args) {
        this.args = args;
    }

    public void setAddress(SocketAddress address) {
        this.address = address;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
