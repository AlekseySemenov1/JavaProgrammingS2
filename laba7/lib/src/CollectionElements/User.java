package CollectionElements;

import java.io.Serializable;

public class User implements Serializable {
    private String login;
    private byte[] password;
    private String salt;

    public User(String login, byte[] password) {
        this.login = login;
        this.password = password;
    }

    public User(String login, byte[] password, String salt) {
        this.login = login;
        this.password = password;
        this.salt = salt;
    }

    public String getLogin() {
        return login;
    }

    public byte[] getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }
}
