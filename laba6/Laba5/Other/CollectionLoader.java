package Other;

import CollectionElements.LabWork;

import javax.naming.NoPermissionException;
import java.io.*;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс для загрузки коллекции из файла
 * и сохранения коллекции в файл.
 */
public class CollectionLoader {
    private File file;
    private Scanner scanner;
    private CSVParser csvParser;
    private LinkedList<LabWork> collection = new LinkedList<>();
    /**
     * Метод для загрузки из файла.
     * enV - переменная окружения.
     * @return коллекция из файла
     */
    public LinkedList<LabWork> readCol() {
        try {
            file = new File(System.getenv("enV"));
            if (!file.canRead() && file.exists()) throw new NoPermissionException();
            scanner = new Scanner(new FileReader(file));
            csvParser = new CSVParser(collection);
            while (scanner.hasNextLine()) {
                csvParser.parse(scanner.nextLine());
            }
            System.out.println("Коллекция добавлена");
            return collection;
        } catch (FileNotFoundException e) {
            System.out.println("Не удалось найти файл");
            readFromFullPath();
        } catch (NullPointerException e) {
            System.out.println("Несуществующая переменная окружения");
            readFromFullPath();
        } catch (NoPermissionException e) {
            System.out.println("Нет прав на чтение файла");
            readFromFullPath();
        }
        return collection;
    }

    /**
     * Метод для сохранения коллекции в файл.
     *
     * @param col  - коллекция для сохранения
     */
    public String writeCol(LinkedList<LabWork> col) {
        try {
            String enVO = "enV";
            File file1 = new File(System.getenv(enVO));
            if (!file1.canWrite() && file1.exists()) throw new NoPermissionException();
            BufferedOutputStream bOS = new BufferedOutputStream(new FileOutputStream(file1));
            for (LabWork laba : col) {
                String q = laba.strParse();
                bOS.write(q.getBytes());
            }
            bOS.close();
            return("Коллекция сохранена");
        } catch (FileNotFoundException e) {
            return ("Не удалось найти файл");
        } catch (NullPointerException e) {
            return ("Несуществующая переменная окружения");
        } catch (IOException e) {
            return ("Ошибка");
        } catch (NoPermissionException e) {
            return ("Нет прав на запись в файл");
        }
    }

    /**
     * Метод для загрузки коллекции из файла,
     * по полному пути к файлу.
     * @return коллекция из файла.
     */
    public LinkedList<LabWork> readFromFullPath() {
        while (true) {
            try {
                System.out.println("Введите полный путь к файлу");
                scanner = new Scanner(System.in);
                file = new File(scanner.nextLine());
                if (!file.exists()) throw new FileNotFoundException();
                if (!file.canRead()) throw new NoPermissionException();
                csvParser = new CSVParser(collection);
                scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    csvParser.parse(scanner.nextLine());
                }
                System.out.println("Коллекция добавлена");
                return collection;
            } catch (IllegalStateException e) {
                System.out.println("Ошибка");
            } catch (NoSuchElementException e) {
                System.out.println("Я упаль");
                System.exit(0);
            } catch (NoPermissionException e) {
                System.out.println("Нет прав на чтение");
            } catch (FileNotFoundException e) {
                System.out.println("Файл не существует");
            }
        }
    }
}
