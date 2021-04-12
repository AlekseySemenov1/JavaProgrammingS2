package Other;

import CollectionElements.LabWork;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Класс для загрузки коллекции из файла
 * и сохранения коллекции в файл.
 */
public class CollectionLoader {

    /**
     * Метод для загрузки из файла.
     * Файл для загрузки: inputlaba5
     * @return collection
     */
    public LinkedList<LabWork> readCol() {
        LinkedList<LabWork> collection = new LinkedList<>();
        try {
            Scanner scanner = new Scanner(new FileReader(System.getenv("inputlaba5")));
            CSVParser csvParser = new CSVParser(collection);
            while (scanner.hasNextLine()) {
                csvParser.parse(scanner.nextLine());
            }
            System.out.println("Коллекция успешно добавлена");
            return collection;
        } catch (FileNotFoundException e) {
            System.out.println("Не удалось найти файл");
        } catch (NullPointerException e){
            System.out.println("Неправильное имя файла");
        }
        return collection;
    }

    /**
     * Метод для сохранения коллекции в файл.
     * Файл для сохранения outlaba5
     * @param col коллекция для записи
     */
    public void writeCol(LinkedList<LabWork> col) {
        try {
            BufferedOutputStream bOS = new BufferedOutputStream(new FileOutputStream(System.getenv("outlaba5")));
            for (LabWork laba : col) {
                String q = laba.strParse();
                bOS.write(q.getBytes());
            }
            bOS.close();
            System.out.println("Коллекция сохранена");
        } catch (FileNotFoundException e) {
            System.out.println("Не удалось найти файл");
        } catch (NullPointerException e){
            System.out.println("Неверное имя файла");
        } catch (IOException e) {
            System.out.println("Ошибка");
        }
    }
}
