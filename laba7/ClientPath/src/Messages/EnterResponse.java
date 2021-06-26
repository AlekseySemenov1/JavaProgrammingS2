package Messages;

import CollectionElements.User;

import java.io.Serializable;
import java.util.ArrayList;

public class EnterResponse implements Serializable {
    private static final long serialVersionUID = 1234567865437896756L;
    private int enterstatus;
    private String message;

    public EnterResponse(String message, int enterstatus) {
        this.enterstatus = enterstatus;
        this.message = message;
    }

    public int isEnterstatus() {
        return enterstatus;
    }

    public String getMessage() {
        return message;
    }
}
