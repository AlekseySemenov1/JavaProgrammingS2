package ServerPath;

import CollectionElements.User;
import Exceptions.DuplicateLoginException;
import Messages.EnterResponse;

import java.net.SocketAddress;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class AuthorizationManager {
    private ArrayList<User> users;
    private ResponseWriter writer;
    private DataBaseConnector connector;

    public AuthorizationManager(ResponseWriter writer, DataBaseConnector connector, ArrayList<User> users) {
        this.writer = writer;
        this.connector = connector;
        this.users = users;
    }

    public void getSalt(String login, SocketAddress address) throws NoSuchAlgorithmException {
        if (users.stream().filter(user -> user.getLogin().equals(login)).count() < 1){
            writer.writeResponse(new EnterResponse("Неправильный логин", 0), address);
            return;
        } else {
            writer.writeResponse(new EnterResponse(users.stream().filter(user -> user.getLogin().equals(login)).reduce("", (data, user) -> data =user.getSalt(), (data, user) -> data), 3), address);
        }
    }

    public void checkPassword(String login, byte[] password, SocketAddress address) {
        if (users.stream().filter(user -> user.getLogin().equals(login)).filter(user -> Arrays.equals(user.getPassword(), password)).count()>0) {
            writer.writeResponse(new EnterResponse("Доступ разрешен", 1), address);
            return;
        }
        writer.writeResponse(new EnterResponse("Неправильный логин или пароль", 0), address);
    }

    public void addNewUser(User user, SocketAddress address) {
        try {
            if (users.stream().filter(user1 -> user1.getLogin().equals(user.getLogin())).count() > 0)
                throw new DuplicateLoginException();
            connector.addNewUserToBase(user);
            users.add(user);
            writer.writeResponse(new EnterResponse("Регистрация прошла успешно", 1), address);
        } catch (SQLException e) {
            System.out.println("Произошла ошибка, не удалось зарегистрировать нового юзера");
            writer.writeResponse(new EnterResponse("Произошла ошибка, не удалось зарегистрировать новый аккаунт", 0), address);
        } catch (DuplicateLoginException e) {
            writer.writeResponse(new EnterResponse("Пользователь с таким именем уже существует", 0), address);
        }
    }

    public void setConnection(SocketAddress address) {
        writer.writeResponse(new EnterResponse("Подключение установлено", 2), address);
    }
}
