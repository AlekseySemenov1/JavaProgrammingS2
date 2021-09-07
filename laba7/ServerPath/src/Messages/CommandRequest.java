package Messages;

import CollectionElements.User;
import Command.Command;

public class CommandRequest<T> extends Request {
    private static final long serialVersionUID = 23454644243534L;
    private Command command;
    private T arg;
    private User user;

    public CommandRequest(Command command, T arg, User user) {
        this.command = command;
        this.arg = arg;
        this.user = user;
    }

    public CommandRequest(Command command, User user) {
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
