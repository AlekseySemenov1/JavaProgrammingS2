package Command;

import CollectionElements.LabWork;
import CollectionElements.User;
import Other.CollectionManager;
import Other.ParametrsInput;

import java.time.ZonedDateTime;

/**
 * Класс для команды remove_lower
 */
public class ComRemoveLower extends CommandAbstract {

    public ComRemoveLower() {
        super("remove_lower", "удалить из коллекции все элементы, меньшие, чем заданный, сравнение происходит по id");
    }

    @Override
    public String execute(Object labWork, CollectionManager colMan, User user) {
        colMan.removeLower((LabWork) labWork, user);
        return "Элементы удалены\n";
    }

    public LabWork askArgs(ParametrsInput pI, User user){
        return new LabWork(
                pI.inputId(),
                pI.inputName(),
                pI.inputCoordinates(),
                ZonedDateTime.now(),
                pI.inputMinimalPoint(),
                pI.inputMaximumPoint(),
                pI.inputAveragePoint(),
                pI.inputDifficulty(),
                pI.inputDiscipline(),
                user.getLogin()
        );
    }
}

