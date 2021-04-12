package Command;

import java.time.ZonedDateTime;

import CollectionElements.LabWork;

import Other.CollectionManager;
import Other.ParametrsInput;

/**
 * Класс для команды add
 */
public class ComAdd extends CommandAbstract{
    private CollectionManager colMan;
    private ParametrsInput pI;

    public ComAdd(CollectionManager colMan, ParametrsInput pI) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.colMan = colMan;
        this.pI = pI;
    }

    @Override
    public void execute(String args) {
        LabWork labWork = new LabWork(
                colMan.newId(),
                pI.inputName(),
                pI.inputCoordinates(),
                ZonedDateTime.now(),
                pI.inputMinimalPoint(),
                pI.inputMaximumPoint(),
                pI.inputAveragePoint(),
                pI.inputDifficulty(),
                pI.inputDiscipline()
        );
        colMan.addNewElement(labWork);
        System.out.println("Элемент успешно добавлен\n");
    }
}
