package Command;

import CollectionElements.LabWork;
import Other.CollectionManager;
import Other.ParametrsInput;

import java.time.ZonedDateTime;

/**
 * Класс для команды remove_lower
 */
public class ComRemoveLower extends CommandAbstract {

    public ComRemoveLower() {
        super("remove_lower", "удалить из коллекции все элементы, меньшие, чем заданный");

    }

    @Override
    public String execute(Object labWork, CollectionManager colMan) {
        colMan.removeLower((LabWork) labWork);
        return "Элементы удалены\n";
    }

    public LabWork askArgs(ParametrsInput pI){
        return new LabWork(
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
    }
}

