package ServerPath;

import CollectionElements.Coordinates;
import CollectionElements.Difficulty;
import CollectionElements.Discipline;
import CollectionElements.LabWork;
import exceptions.DuplicateIdException;
import exceptions.OutOfLimitException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;

public class Validator {

    public Validator() {
    }

    public void checkCollectionElements(ResultSet resultSet, LinkedList<LabWork> collection) throws OutOfLimitException, DuplicateIdException {
        try {
            for (LabWork laba : collection) {
                if (resultSet.getLong(1) == laba.getId()) throw new DuplicateIdException();
            }
            if (resultSet.getDouble(3) > 236 | resultSet.getInt(4) > 988 |
                    resultSet.getFloat(6) < 0 | resultSet.getDouble(7) < 0 |
                    resultSet.getDouble(8) < 0 | resultSet.getInt(11) < 0 |
                    resultSet.getLong(12) < 0) throw new OutOfLimitException();
        } catch (SQLException throwables) {
            System.out.println("Не удалось получить данные из бд");
        }
    }
}
