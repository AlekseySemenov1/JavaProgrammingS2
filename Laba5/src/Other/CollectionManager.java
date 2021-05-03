package Other;

import CollectionElements.Difficulty;
import CollectionElements.Discipline;
import CollectionElements.LabWork;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Класс для работы с коллекцией.
 * Является классом Receiver для шаблона Command
 */
public class CollectionManager {
    private LinkedList<LabWork> labCol = new LinkedList<>();
    private ZonedDateTime initTime = null;
    private CollectionLoader loader;

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
    public void showCollection() {
        for (LabWork laba: labCol){
            System.out.println(laba.toString());
            System.out.println();
        }
    }

    /**
     * Метод добавляет новый элемент в коллекцию
     * @param labwork заданный элемент коллекции
     */
    public void addNewElement(LabWork labwork) {
        labCol.add(labwork);
    }

    /**
     * Метод удаляет элемент коллекции
     * @param labwork заданный элемент коллекции
     */
    public void removeElement(LabWork labwork) {
        labCol.remove(labwork);
    }

    /**
     * Метод удаляет элементы коллекции
     * меньше заданного
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
     * @return id
     */
    public long newId() {
        if (labCol.isEmpty()) return 1;
        else
            return labCol.getLast().getId() + 1;
    }

    /**
     * Выводит информацию об элементах значение
     * поля difficulty которых равно заданному
     * @param difficulty заданной поле
     */
    public void filterDiff(Difficulty difficulty) {
        String elements = "";
        for (LabWork laba : labCol) {
            if (laba.getDifficulty().equals(difficulty))
                System.out.println(laba);
        }
    }

    /**
     * Удаляет элемент коллекции по id
     * @param id заданное id
     */
   public boolean removeById(Long id) {
        boolean q = false;
        for (LabWork laba : labCol) {
            if (laba.getId() == id) {
                labCol.remove(laba);
                q = true;
            }
        }
        return q;
    }
    
    public int countLessDiscipline(Discipline discipline) {
        int count = 0;
        for (LabWork laba : labCol) {
            if (laba.getDiscipline().compareTo(discipline) < 0) {
                count++;
            }
        }
        return count;
    }

    /**
     * Выводит поля difficulty всех
     * элементов в порядке убывания
     */
    public void printFieldDifficulty() {
        ArrayList<Difficulty> diff = new ArrayList<>();
        for (LabWork laba: labCol){
            diff.add(laba.getDifficulty());
        }
        diff.sort(new Comparator<Difficulty>() {
            @Override
            public int compare(Difficulty o1, Difficulty o2) {
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
            }
        });
        for (Difficulty d:diff){
            System.out.println(d);
        }
    }
    
    /**
     * Сохраняет коллекцию
     */
    public void save(){
        loader.writeCol(labCol);
    }

    /**
     * Загружает коллекцию
     */
    public void load(){
        labCol = loader.readCol();
        initTime = ZonedDateTime.now();
    }
}
