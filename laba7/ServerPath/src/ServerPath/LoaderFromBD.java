package ServerPath;

import CollectionElements.*;
import exceptions.DuplicateIdException;
import exceptions.OutOfLimitException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.LinkedList;

public class LoaderFromBD {
    private Connection connection;
    private Validator validator;

    public LoaderFromBD(Connection connection, Validator validator) {
        this.connection = connection;
        this.validator = validator;
    }

    public ArrayList<User> loadUserList() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * from users");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                userList.add(new User(resultSet.getString(1), resultSet.getBytes(2), resultSet.getString(3)));
            }
            return userList;
        } catch (SQLException e) {
            System.out.println("Не удалось загрузить список юзеров");
        }
        return null;
    }

    public LinkedList<LabWork> loadCollectionFromBD() {
        try {
            PreparedStatement statement = connection.prepareStatement("Select * from collection");
            ResultSet resultSet = statement.executeQuery();
            LinkedList<LabWork> collection = new LinkedList<>();
            while (resultSet.next()) {
                try {
                    validator.checkCollectionElements(resultSet, collection);
                    collection.add(new LabWork(
                            resultSet.getLong(1),
                            resultSet.getString(2),
                            new Coordinates(resultSet.getDouble(3), resultSet.getInt(4)),
                            ZonedDateTime.parse((CharSequence) resultSet.getString(5)),
                            resultSet.getFloat(6),
                            resultSet.getDouble(7),
                            resultSet.getDouble(8),
                            Difficulty.valueOf(resultSet.getString(9).toUpperCase()),
                            new Discipline(resultSet.getString(10), resultSet.getInt(11), resultSet.getLong(12)),
                            resultSet.getString(13)
                    ));
                } catch (DateTimeParseException e) {
                    System.out.println("Какая-то странная дата: " + resultSet.getDate(5));
                    System.out.println("Элемент пропущен");
                } catch (IllegalArgumentException e) {
                    System.out.println("Заданное значение поля difficulty не существует");
                    System.out.println("элемент пропущен");
                } catch (DuplicateIdException e) {
                    System.out.println("Элемент с таким Id уже существует");
                    System.out.println("Элемент пропущен");
                } catch (OutOfLimitException e) {
                    System.out.println("Выход за допустимые значения переменной");
                    System.out.println("Элемент пропущен");
                }
            }

            return collection;
        } catch (SQLException e) {
            System.out.println("Не удалось загрузить коллекцию из базы данных");
            e.printStackTrace();
        }
        return null;
    }
}
