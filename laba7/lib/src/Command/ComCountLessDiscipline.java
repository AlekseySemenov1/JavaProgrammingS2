package Command;

import CollectionElements.Discipline;
import CollectionElements.User;
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
    public String execute(Object args, CollectionManager colMan, User user) {
        try {
            if (colMan.getLabCol().isEmpty()) throw new NullPointerException();
            return "Кол-во элементов: " + colMan.countLessDiscipline((Discipline) args) + "\n";
        } catch (NullPointerException e){
            setExecuteStatus(0);
            return "Коллекция пуста";
        }
    }

    public Discipline askArgs(ParametrsInput pI){
        return new Discipline(pI.inputDisciplineName(), pI.inputPracticeHours(), pI.inputSelfStudyHours());
    }
}
