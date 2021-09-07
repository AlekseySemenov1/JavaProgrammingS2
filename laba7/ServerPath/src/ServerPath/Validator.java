package ServerPath;

import CollectionElements.*;
import exceptions.DuplicateIdException;
import exceptions.OutOfLimitException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Validator {

    public Validator() {
    }

    public void checkCollectionElements(ResultSet resultSet, LinkedList<LabWork> collection) throws OutOfLimitException, DuplicateIdException {
        try {
            for (LabWork laba : collection) {
                if (resultSet.getLong(1) == laba.getId()) throw new DuplicateIdException();
            }
            if (resultSet.getString(2).equals("") | Math.abs(resultSet.getDouble(3)) > 236 | Math.abs(resultSet.getInt(4)) > 988 |
                    resultSet.getFloat(6) < 0 | resultSet.getDouble(7) < 0 |
                    resultSet.getDouble(8) < 0 | resultSet.getInt(11) < 0 |
                    resultSet.getLong(12) < 0) throw new OutOfLimitException();
        } catch (SQLException throwables) {
            System.out.println("Не удалось получить данные из бд");
        }
    }

    public void checkUsersList(ResultSet resultSet, ArrayList<User> users) throws DuplicateIdException {
        try {
            for (User user : users) {
                if (user.getLogin().equals(resultSet.getString(1))) throw new DuplicateIdException();
            }
        } catch (SQLException throwables) {
            System.out.println("Не удалось получить данные из бд");
        }
    }
}
