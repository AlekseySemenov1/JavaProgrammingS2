package Other;

import CollectionElements.Difficulty;
import CollectionElements.Discipline;
import CollectionElements.LabWork;
import CollectionElements.User;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Класс для работы с коллекцией.
 * Является классом Receiver для шаблона Command
 */
public class CollectionManager {
    private LinkedList<LabWork> labCol = new LinkedList<>();
    private ZonedDateTime initTime = null;

    public CollectionManager(ZonedDateTime initTime, LinkedList<LabWork> labCol) {
        this.initTime = initTime;
        this.labCol = labCol;
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
        return labCol.stream().reduce("", (data, q) -> data += q, (data, q) -> data + q).trim();
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
     * @return
     */
    public int removeElement(LabWork labwork, User user) {
        if (labwork.getUser().equals(user.getLogin())){
            labCol.remove(labwork);
            return 1;
        }
        return 2;
    }

    /**
     * Метод удаляет элементы коллекции
     * меньше заданного
     *
     * @param labwork заданный элемент коллекции
     * @return
     */
    public void removeLower(LabWork labwork, User user) {
        labCol.removeIf(laba -> laba.getUser().equals(user.getLogin()) && laba.compareTo(labwork) < 1);
    }

    /**
     * Очищает коллекцию
     */
    public String colClear(User user) {
        labCol.removeIf(laba->laba.getUser().equals(user.getLogin()));
        return "Все ваши элементы удалены";
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
        return labCol.stream().filter(laba -> laba.getDifficulty().equals(difficulty)).reduce("", (data, q) -> data += q, (data, q) -> data + q).trim();
    }

    public int findElementById(Long id, User user) {
        Iterator<LabWork> lI = labCol.iterator();
        while (lI.hasNext()) {
            LabWork laba = lI.next();
            if (laba.getId() == id)
                if (laba.getUser().equals(user.getLogin()))
                    return 1;
                else return 2;
        }
        return 0;
    }

    /**
     * Удаляет элемент коллекции по id
     *
     * @param id заданное id
     */
    public int removeById(Long id, User user) {
        Iterator<LabWork> lI = labCol.iterator();
        while (lI.hasNext()) {
            LabWork laba = lI.next();
            if (laba.getId() == id) {
                if (laba.getUser().equals(user.getLogin())) {
                    labCol.remove(laba);
                    return 1;
                }
                return 2;
            }
        }
        return 0;
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
        labCol.forEach(laba -> diff.add(laba.getDifficulty()));
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
        return diff.stream().reduce("", (s, difficulty) -> s += difficulty + "\n", (s, difficulty) -> s + difficulty).trim();
    }

    public void sortByCoordinates() {
        labCol.sort((o1, o2) -> {
            if ((Math.pow(o1.getCoordinates().getX(), 2) + Math.pow(o1.getCoordinates().getY(), 2)) > (Math.pow(o2.getCoordinates().getX(), 2) + Math.pow(o2.getCoordinates().getY(), 2)))
                return 1;
            else
                return -1;
        });
    }
}
