package Messages;

import java.io.Serializable;

public class Response implements Serializable {
    private static final long serialVersionUID = 5432167548586556L;
    private String message;
    private int executeStatus;

    public Response(String message, int executeStatus) {
        this.message = message;
        this.executeStatus = executeStatus;
    }

    public String getMessage() {
        return message;
    }

    public int isExecuteStatus() {
        return executeStatus;
    }
}
