package Command;

import CollectionElements.LabWork;
import Other.CollectionManager;
import Other.ParametrsInput;

import java.time.ZonedDateTime;

/**
 * Класс для команды remove_lower
 */
public class ComRemoveLower extends CommandAbstract {
    private CollectionManager colMan;
    private ParametrsInput pI;

    public ComRemoveLower(CollectionManager colMan, ParametrsInput pI) {
        super("remove_lower {element}", "удалить из коллекции все элементы, меньшие, чем заданный");
        this.colMan = colMan;
        this.pI =pI;
    }

    @Override
    public void execute(String args) {
        LabWork labWork = new LabWork(
                pI.inputId(),
                pI.inputName(),
                pI.inputCoordinates(),
                ZonedDateTime.now(),
                pI.inputMinimalPoint(),
                pI.inputMaximumPoint(),
                pI.inputAveragePoint(),
                pI.inputDifficulty(),
                pI.inputDiscipline()
        );
        colMan.removeLower(labWork);
        System.out.println("Элементы удалены\n");
    }
}

