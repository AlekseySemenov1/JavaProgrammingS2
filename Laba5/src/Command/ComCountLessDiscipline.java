package Command;

import CollectionElements.Discipline;

import Other.CollectionManager;
import Other.ParametrsInput;

/**
 * Класс для команды count_less_than_discipline
 */
public class ComCountLessDiscipline extends CommandAbstract {
    private ParametrsInput pI;
    private CollectionManager colMan;

    public ComCountLessDiscipline(CollectionManager colman, ParametrsInput pI) {
        super("count_less_than_discipline","вывести количество элементов, значение поля discipline которых меньше заданного");
        this.colMan = colMan;
        this.pI = pI;
    }

    @Override
    public void execute(String args) {
        Discipline discipline = new Discipline(pI.inputDisciplineName(), pI.inputPracticeHours(), pI.inputSelfStudyHours());
        System.out.println("Кол-во элементов: " + colMan.countLessDiscipline(discipline) + "\n");
    }
}
