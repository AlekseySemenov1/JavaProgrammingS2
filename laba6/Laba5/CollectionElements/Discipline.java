package CollectionElements;

import java.io.Serializable;
import java.util.Objects;

public class Discipline implements Comparable<Discipline>, Serializable {
    private String name;
    private int practiceHours;
    private long selfStudyHours;

    public Discipline(String name, int pH, long sSH){
        this.name = name;
        this.practiceHours = pH;
        this.selfStudyHours = sSH;
    }

    public String getName() {
        return name;
    }

    public int getPracticeHours() {
        return practiceHours;
    }

    public long getSelfStudyHours() {
        return selfStudyHours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discipline that = (Discipline) o;
        return practiceHours == that.practiceHours && selfStudyHours == that.selfStudyHours && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, practiceHours, selfStudyHours);
    }

    @Override
    public int compareTo(Discipline o) {
        return (this.practiceHours - o.getPracticeHours());
    }
}
