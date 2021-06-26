package ServerPath;

import CollectionElements.Coordinates;
import CollectionElements.LabWork;
import CollectionElements.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

public class DataBaseConnector {
    private String url;
    private String login;
    private String password;
    private Connection connection;

    public DataBaseConnector(String url, String login, String password) {
        this.url = url;
        this.login = login;
        this.password = password;
    }

    public Connection ConnectToDataBase() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, login, password);
            System.out.println("Подключение к бд");
            return connection;
        } catch (ClassNotFoundException e) {
            System.out.println("Помогите Даше найти дрова для бд");
        } catch (SQLException e) {
            System.out.println("Неизвестная ошибка");
        }
        return null;
    }

    public void addNewUserToBase(User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("insert into users (login, password, salt) VALUES (?,?,?)");
        statement.setString(1, user.getLogin());
        statement.setBytes(2, user.getPassword());
        statement.setString(3, user.getSalt());
        statement.execute();
    }

    public void addNewElementToBase(LabWork laba) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("insert into collection (id, name, x, y, creationdate, maximumpoint, minimumpoint, averagepoint, difficulty, namediscipline, practicehours, selfstudyhours, username) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
        statement.setLong(1, laba.getId());
        statement.setString(2, laba.getName());
        statement.setDouble(3, laba.getCoordinates().getX());
        statement.setInt(4, laba.getCoordinates().getY());
        statement.setString(5, laba.getCreationDate().toString());
        statement.setDouble(6, laba.getMaximumPoint());
        statement.setFloat(7, laba.getMinimalPoint());
        statement.setDouble(8, laba.getAveragePoint());
        statement.setString(9, laba.getDifficulty().toString());
        statement.setString(10, laba.getDiscipline().getName());
        statement.setInt(11, laba.getDiscipline().getPracticeHours());
        statement.setLong(12, laba.getDiscipline().getSelfStudyHours());
        statement.setString(13, laba.getUser());
        statement.execute();
    }

    public void deleteElementById(long id, String user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("delete from collection where id = ? and username = ?");
        statement.setLong(1, id);
        statement.setString(2, user);
        statement.execute();
    }

    public void deleteAllElementLowerThan(Long id, String user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("delete from collection where id < ? and username = ?");
        statement.setLong(1, id);
        statement.setString(2, user);
        statement.execute();
    }

    public void deleteAllUserElement(String user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("delete from collection where username = ?");
        statement.setString(1, user);
        statement.execute();
    }

    public long getNewId() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select nextval('Idgenerator')");
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next())
            return resultSet.getLong(1);
        else throw new SQLException();
    }
}
