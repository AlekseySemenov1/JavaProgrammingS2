package Messages;

import CollectionElements.User;

import java.io.Serializable;

public class RegisterRequest implements Serializable {
    private static final long serialVersionUID = 3454675678689L;
    private User user;

    public RegisterRequest(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
