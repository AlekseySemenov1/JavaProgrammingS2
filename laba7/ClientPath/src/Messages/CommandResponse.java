package Messages;

public class CommandResponse extends Response {
    private static final long serialVersionUID = 5432167548586556L;

    public CommandResponse(String message, int status) {
        super(message, status);
    }
}
