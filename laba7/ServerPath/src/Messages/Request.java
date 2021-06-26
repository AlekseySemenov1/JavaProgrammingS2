package Messages;

import CollectionElements.User;
import Command.Command;

import java.io.Serializable;

public class Request<T> implements Serializable {
    private static final long serialVersionUID = 23454644243534L;
    private Command command;
    private T arg;
    private User user;

    public Request(Command command, T arg, User user) {
        this.command = command;
        this.arg = arg;
        this.user = user;
    }

    public Request(Command command, User user) {
        this.command = command;
        this.user = user;
    }

    public Command getCommand() {
        return command;
    }

    public T getArg() {
        return arg;
    }

    public User getUser() {
        return user;
    }
}
