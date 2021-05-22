package Messages;

import Command.Command;

import java.io.Serializable;

public class Request<T> implements Serializable {
    private Command command;
    private T arg;

    public Request(Command command, T arg) {
        this.command = command;
        this.arg = arg;
    }

    public Request(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    public T getArg() {
        return arg;
    }
}
