package Command;

import CollectionElements.LabWork;
import CollectionElements.User;
import Other.CollectionManager;
import Other.ParametrsInput;

import java.time.ZonedDateTime;

/**
 * Класс для команды add
 */
public class ComAdd extends CommandAbstract{

    public ComAdd() {
        super("add", "добавить новый элемент в коллекцию");
    }

    @Override
    public String execute(Object labWork, CollectionManager colMan, User user) {
        ((LabWork) labWork).setCreationDate(ZonedDateTime.now());
        colMan.addNewElement((LabWork) labWork);
        return "Элемент успешно добавлен\n";
    }

    public LabWork askArgs(ParametrsInput pI, User user){
        return new LabWork(
                0,
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
