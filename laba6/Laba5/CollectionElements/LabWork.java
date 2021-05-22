package CollectionElements;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Класс элементов коллекции.
 * Содержит геттеры для вывода полей
 */
public class LabWork implements Comparable<LabWork>, Serializable {
    private long id;
    private String name;
    private Coordinates coordinates;
    private ZonedDateTime creationDate;
    private Float minimalPoint;
    private double maximumPoint;
    private Double averagePoint;
    private Difficulty difficulty;
    private Discipline discipline;

    public LabWork(long id, String name, Coordinates coordinates, ZonedDateTime creationDate,
                   Float minimalPoint, double maximumPoint, Double averagePoint,
                   Difficulty difficulty, Discipline discipline) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.maximumPoint = maximumPoint;
        this.minimalPoint = minimalPoint;
        this.averagePoint = averagePoint;
        this.difficulty = difficulty;
        this.discipline = discipline;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public double getMaximumPoint() {
        return maximumPoint;
    }

    public long getId() {
        return id;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public Double getAveragePoint() {
        return averagePoint;
    }

    public Float getMinimalPoint() {
        return minimalPoint;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Производит сравнение элементов по их id
     * @param o объект сравнения
     * @return (int) (id - o.getId())
     */
    @Override
    public int compareTo(LabWork o) {
        return (int) (id - o.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LabWork labWork = (LabWork) o;
        return id == labWork.id && Double.compare(labWork.maximumPoint, maximumPoint) == 0 && Objects.equals(name, labWork.name) && Objects.equals(coordinates, labWork.coordinates) && Objects.equals(creationDate, labWork.creationDate) && Objects.equals(minimalPoint, labWork.minimalPoint) && Objects.equals(averagePoint, labWork.averagePoint) && difficulty == labWork.difficulty && Objects.equals(discipline, labWork.discipline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, minimalPoint, maximumPoint, averagePoint, difficulty, discipline);
    }

    @Override
    public String toString() {
        return  "id: " + id + "\n" +
                "name: '" + name + '\'' + "\n" +
                "coordinates \nX: " + coordinates.getX() + "\n" +
                "Y: " + coordinates.getY() + "\n" +
                "creationDate:" + creationDate + "\n" +
                "minimalPoint:" + minimalPoint + "\n" +
                "maximumPoint:" + maximumPoint + "\n" +
                "averagePoint:" + averagePoint + "\n" +
                "difficulty:" + difficulty + "\n" +
                "discipline \nname: " + discipline.getName() + "\n" +
                "practicehours: " + discipline.getPracticeHours() + "\n" +
                "selfstudyhours: " + discipline.getSelfStudyHours()+"\n\n";
    }

    /**
     * Метод парсер для сохранения коллекции в файл
     * @return строка в csv формате
     * с запятой в качестве разделителя
     */
    public String strParse(){
        return id + "," + name + "," +  coordinates.getX() + "," +
               coordinates.getY() + "," + creationDate + "," + minimalPoint + "," +
                maximumPoint + "," + averagePoint + "," + difficulty + "," +
                discipline.getName() + "," + discipline.getPracticeHours() + "," +
                discipline.getSelfStudyHours() + "\n";
    }
}
