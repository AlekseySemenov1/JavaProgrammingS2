package Messages;

import CollectionElements.User;

import java.io.Serializable;

public class EnterRequest implements Serializable {
    private static final long serialVersionUID = 98765423456754L;
    private User user;

    public EnterRequest(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
