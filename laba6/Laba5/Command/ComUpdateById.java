package Command;

import CollectionElements.LabWork;
import Other.CollectionManager;
import Other.ParametrsInput;
import exceptions.EmptyInputException;

import java.time.ZonedDateTime;

/**
 * Класс для команды update_be_id
 */
public class ComUpdateById extends CommandAbstract {

    public ComUpdateById() {
        super("update_by_id", "обновить значение элемента коллекции, id которого равен заданному");

    }
    @Override
    public String execute(Object args, CollectionManager colMan) {
        try {
            if (!colMan.removeById(((LabWork) args).getId())) throw new NullPointerException();
            ((LabWork) args).setCreationDate(ZonedDateTime.now());
            colMan.addNewElement((LabWork) args);
            return "Элемент успешно обновлен\n";
        } catch (NumberFormatException e) {
            return "Вводимый параметр должен быть числом";
        } catch (NullPointerException e){
            return "Элемент с таким id не найден";
        }
    }

    public LabWork askArgs(String args, ParametrsInput pI){
        try {
            if ((args.isEmpty())) throw new EmptyInputException();
            return new LabWork(
                    Long.parseLong(args),
                    pI.inputName(),
                    pI.inputCoordinates(),
                    ZonedDateTime.now(),
                    pI.inputMinimalPoint(),
                    pI.inputMaximumPoint(),
                    pI.inputAveragePoint(),
                    pI.inputDifficulty(),
                    pI.inputDiscipline()
            );
        } catch (EmptyInputException e) {
            System.out.println("Нет аргумента");
        }
        return null;
    }
}