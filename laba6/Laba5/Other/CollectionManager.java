package Other;

import CollectionElements.Difficulty;
import CollectionElements.Discipline;
import CollectionElements.LabWork;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Класс для работы с коллекцией.
 * Является классом Receiver для шаблона Command
 */
public class CollectionManager {
    private LinkedList<LabWork> labCol = new LinkedList<>();
    private ZonedDateTime initTime = null;
    private CollectionLoader loader = null;

    public CollectionManager(ZonedDateTime initTime, CollectionLoader loader) {
        this.initTime = initTime;
        this.loader = loader;
        load();
    }

    /**
     * @return все элементы коллекции
     */
    public LinkedList<LabWork> getLabCol() {
        return labCol;
    }

    /**
     * @return информация о коллекции
     */
    public String collectionInfo() {
        return "Тип коллекции: " + getLabCol().getClass().getName() + "\n" +
                "Размер коллекции: " + labCol.size() + "\n" +
                "Дата инциализации: " + initTime;
    }

    /**
     * @return первый элемент коллекции
     */
    public LabWork getFirst() {
        if (labCol.isEmpty()) return null;
        return labCol.getFirst();
    }

    /**
     * Выводит информацию о всех элементах коллекции
     */
    public String showCollection() {
        return labCol.stream().reduce("", (data, q) -> data += q, (data,q) -> data + q).trim();
    }

    /**
     * Метод добавляет новый элемент в коллекцию
     *
     * @param labwork заданный элемент коллекции
     */
    public void addNewElement(LabWork labwork) {
        labCol.add(labwork);
        sortByCoordinates();
    }

    /**
     * Метод удаляет элемент коллекции
     *
     * @param labwork заданный элемент коллекции
     */
    public void removeElement(LabWork labwork) {
        labCol.remove(labwork);
    }

    /**
     * Метод удаляет элементы коллекции
     * меньше заданного
     *
     * @param labwork заданный элемент коллекции
     */
    public void removeLower(LabWork labwork) {
        labCol.removeIf(labWork -> labWork.compareTo(labwork) < 1);
    }

    /**
     * Очищает коллекцию
     */
    public void colClear() {
        labCol.clear();
    }

    /**
     * Генерирует новый id
     *
     * @return id
     */
    public long newId() {
        if (labCol.isEmpty()) return 1;
        else {
            labCol.sort(LabWork::compareTo);
            return labCol.getLast().getId() + 1;
        }
    }

    /**
     * Выводит информацию об элементах значение
     * поля difficulty которых равно заданному
     *
     * @param difficulty заданной поле
     */
    public String filterDiff(Difficulty difficulty) {
        return labCol.stream().filter(laba -> laba.getDifficulty().equals(difficulty)).reduce("", (data,q) -> data +=q, (data, q)-> data + q).trim();
    }

    /**
     * Удаляет элемент коллекции по id
     *
     * @param id заданное id
     */
    public boolean removeById(Long id) {
        Iterator<LabWork> lI = labCol.iterator();
        while (lI.hasNext()) {
            labCol.removeIf(labWork -> labWork.getId() == id);
            return true;
        }
        return false;
    }

    /**
     * @param discipline - значение поля discipline для сравнения.
     * @return кол-во элементов, поле discipline
     * которых меньше заданного.
     */
    public int countLessDiscipline(Discipline discipline) {
        return (int) labCol.stream().filter(laba -> laba.getDiscipline().compareTo(discipline) < 0).count();
    }

    /**
     * Выводит поля difficulty всех
     * элементов в порядке убывания
     */

    public String printFieldDifficulty() {
        ArrayList<Difficulty> diff = new ArrayList<>();
        labCol.stream().forEach(laba -> diff.add(laba.getDifficulty()));
        diff.sort((o1, o2) -> {
            int q1, q2;
            if (o1.equals(Difficulty.TERRIBLE))
                q1 = 2;
            else if (o1.equals(Difficulty.HOPELESS))
                q1 = 1;
            else q1 = 0;
            if (o2.equals(Difficulty.TERRIBLE))
                q2 = 2;
            else if (o2.equals(Difficulty.HOPELESS))
                q2 = 1;
            else q2 = 0;
            return q1 - q2;
        });
        return diff.stream().reduce("", (s, difficulty) -> s += difficulty + "\n", (s,difficulty) -> s + difficulty).trim();
    }

    public void sortByCoordinates(){
        labCol.sort((o1, o2) -> {
            if ((Math.pow(o1.getCoordinates().getX(), 2) + Math.pow(o1.getCoordinates().getY(), 2)) > (Math.pow(o2.getCoordinates().getX(),2) + Math.pow(o2.getCoordinates().getY(),2)))
                return 1;
            else
                return -1;
        });
    }

    /**
     * Сохраняет коллекцию
     */
    public String save() {
        return loader.writeCol(labCol);
    }

    /**
     * Загружает коллекцию
     */
    public void load() {
        labCol = loader.readCol();
        initTime = ZonedDateTime.now();
    }


}
