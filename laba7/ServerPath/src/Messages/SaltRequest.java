package Messages;

import java.io.Serializable;

public class SaltRequest implements Serializable {
    private String login;

    public SaltRequest(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }
}
