package Messages;

import CollectionElements.User;

public class EnterRequest extends Request {
    private static final long serialVersionUID = 98765423456754L;
    private User user;

    public EnterRequest(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
