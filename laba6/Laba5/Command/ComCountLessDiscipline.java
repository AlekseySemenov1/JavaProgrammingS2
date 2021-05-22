package Command;

import CollectionElements.Discipline;
import Other.CollectionManager;
import Other.ParametrsInput;

/**
 * Класс для команды count_less_than_discipline
 */
public class ComCountLessDiscipline extends CommandAbstract {

    public ComCountLessDiscipline() {
        super("count_less_than_discipline","вывести количество элементов, значение поля discipline которых меньше заданного");
    }

    @Override
    public String execute(Object args, CollectionManager colMan) {
        try {
            if (colMan.getLabCol().isEmpty()) throw new NullPointerException();
            return "Кол-во элементов: " + colMan.countLessDiscipline((Discipline) args) + "\n";
        } catch (NullPointerException e){
            return "Коллекция пуста";
        }
    }

    public Discipline askArgs(ParametrsInput pI){
        return new Discipline(pI.inputDisciplineName(), pI.inputPracticeHours(), pI.inputSelfStudyHours());
    }
}
