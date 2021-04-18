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
    String enV = null;
    
    public CollectionLoader(String enV) {
        this.enV = enV;
    }
    
   /**
     * Метод для загрузки из файла.
     * enV - переменная окружения.
     * @return коллекция из файла
     */
    public LinkedList<LabWork> readCol() {
        LinkedList<LabWork> collection = new LinkedList<>();
        try {
            Scanner scanner = new Scanner(new FileReader(System.getenv(enV)));
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
     * @param col - коллекция для сохранения
     * @param enVO - переменная окружения
     */
    public void writeCol(LinkedList<LabWork> col, String enVO) {
        try {
            if (enVO.equals("")) enVO = "outlaba5";
            BufferedOutputStream bOS = new BufferedOutputStream(new FileOutputStream(System.getenv(enVO)));
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
