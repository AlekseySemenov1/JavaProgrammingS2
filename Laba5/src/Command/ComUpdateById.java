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
    private CollectionManager colMan;
    private ParametrsInput pI;

    public ComUpdateById(CollectionManager colMan, ParametrsInput pI) {
        super("update_by_id {element}", "обновить значение элемента коллекции, id которого равен заданному");
        this.colMan = colMan;
        this.pI = pI;
    }

    @Override
    public void execute(String args) {
        try {
            if (args.isEmpty()) throw new EmptyInputException();
            long id = Long.parseLong(args);
            if (!colMan.removeById(id)) throw new NullPointerException();
            colMan.addNewElement(new LabWork(
                    id,
                    pI.inputName(),
                    pI.inputCoordinates(),
                    ZonedDateTime.now(),
                    pI.inputMinimalPoint(),
                    pI.inputMaximumPoint(),
                    pI.inputAveragePoint(),
                    pI.inputDifficulty(),
                    pI.inputDiscipline()
            ));
            System.out.println("Элемент успешно обновлен\n");
        } catch (NumberFormatException e) {
            System.out.println("Вводимый параметр должен быть числом");
        } catch (EmptyInputException e) {
            System.out.println("Ввод пустой строки");
        } catch (NullPointerException e){
            System.out.println("Элемент с таким id не найден");
        }
    }
}
