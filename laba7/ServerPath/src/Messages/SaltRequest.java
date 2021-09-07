package Messages;

public class SaltRequest extends Request {
    private static final long serialVersionUID = 567956794456L;
    private String login;

    public SaltRequest(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }
}
