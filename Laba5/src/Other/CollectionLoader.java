package Other;

import CollectionElements.LabWork;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import javax.naming.NoPermissionException;

/**
 * Класс для загрузки коллекции из файла
 * и сохранения коллекции в файл.
 */
public class CollectionLoader {
    
   /**
     * Метод для загрузки из файла.
     * enV - переменная окружения.
     * @return коллекция из файла
     */
    public LinkedList<LabWork> readCol() {
        LinkedList<LabWork> collection = new LinkedList<>();
        try {
            File file = new File(System.getenv("enV"));
            if (!file.canRead()&& file.exists()) throw new NoPermissionException();
            Scanner scanner = new Scanner(new FileReader(file));
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
        } catch (NoPermissionException e) {
            System.out.println("Нет прав на чтение файла");
        }
        return collection;
    }

    /**
     * Метод для сохранения коллекции в файл.
     * @param col - коллекция для сохранения
     * @param enVO - переменная окружения
     */
    public void writeCol(LinkedList<LabWork> col, String enVO) {
        try {
            if (enVO.equals("")) enVO = "enV";
            File file = new File(System.getenv(enVO));
            if (!file.canWrite()&& file.exists()) throw new NoPermissionException();
            BufferedOutputStream bOS = new BufferedOutputStream(new FileOutputStream(file));
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
        } catch (NoPermissionException e) {
            System.out.println("Нет прав на запись в файл");
        }
    }
}
