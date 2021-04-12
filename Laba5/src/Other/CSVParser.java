package Other;

import CollectionElements.Coordinates;
import CollectionElements.Difficulty;
import CollectionElements.Discipline;
import CollectionElements.LabWork;
import exceptions.DuplicateIdException;
import exceptions.OutOfLimitException;

import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;

/**
 * Парсер для загрузки коллекции из csv файла.
 */
public class CSVParser {
    private LinkedList<LabWork> collection;

    public CSVParser(LinkedList<LabWork> collection) {
        this.collection = collection;
    }

    /**
     * Делит строку из файла на 12 частей(параметры элемента коллекции),
     * парсит параметры и добавляет элемент в коллекцию.
     * @param str строка из файла
     */
    public void parse(String str) {
        String[] fields = new String[12];
        try {
            fields = str.split(",", 12);
            for (LabWork laba : collection) {
                if (Long.parseLong(fields[0]) == laba.getId()) throw new DuplicateIdException();
            }
            if (fields[1].isEmpty()) throw new NullPointerException();
            if (Double.parseDouble(fields[2]) > 236 | Integer.parseInt(fields[3]) > 988 |
                    Float.parseFloat(fields[5]) < 0 | Double.parseDouble(fields[6]) < 0 |
                    Double.parseDouble(fields[7]) < 0 | Integer.parseInt(fields[10]) < 0 |
                    Long.parseLong(fields[11]) < 0) throw new OutOfLimitException();
            collection.add(new LabWork(Long.parseLong(fields[0]),
                    fields[1],
                    new Coordinates(Double.parseDouble(fields[2]), Integer.parseInt(fields[3])),
                    ZonedDateTime.parse(fields[4]),
                    Float.parseFloat(fields[5]),
                    Double.parseDouble(fields[6]),
                    Double.parseDouble(fields[7]),
                    Difficulty.valueOf(fields[8].toUpperCase()),
                    new Discipline(fields[9], Integer.parseInt(fields[10]), Long.parseLong(fields[11]))
            ));
        } catch (DateTimeParseException e) {
            System.out.println("Какая-то странная дата: " + fields[4]);
            System.out.println("Элемент " + (collection.size() + 1) + " пропущен");
        } catch (IllegalArgumentException e) {
            System.out.println("Неверное значение аргумента, элемент " + (collection.size() + 1) + " пропущен ");
        } catch (NullPointerException e) {
            System.out.println("Ничего страшного");
            System.out.println("Элемент " + (collection.size() + 1) + " пропущен");
        } catch (DuplicateIdException e) {
            System.out.println("Элемент с таким Id уже существует");
            System.out.println("Элемент " + (collection.size() + 1) + " пропущен");
        } catch (OutOfLimitException e) {
            System.out.println("Выход за допустимые значения переменной");
            System.out.println("Элемент " + (collection.size() + 1) + " пропущен");
        }
    }
}
