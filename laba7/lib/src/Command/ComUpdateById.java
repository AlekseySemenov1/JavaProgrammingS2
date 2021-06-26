package Command;

import CollectionElements.LabWork;
import CollectionElements.User;
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
    public String execute(Object args, CollectionManager colMan, User user) {
        try {
            ((LabWork) args).setCreationDate(ZonedDateTime.now());
            colMan.removeById(((LabWork) args).getId(), user);
            colMan.addNewElement((LabWork) args);
            return "Элемент успешно обновлен\n";
        } catch (NumberFormatException e) {
            return "Вводимый параметр должен быть числом";
        }
    }

    public String checkElement(String args, CollectionManager colMan, User user){
        setExecuteStatus(-1);
        setExecuteStatus(colMan.findElementById(Long.parseLong(args), user));
        if (getExecuteStatus() == 1)
            return "Запрос на изменение элемента одобрен";
        else if (getExecuteStatus() == 2)
            return "Данный элемент принадлежит не вам, вы не можете его изменять";
        else return "Элемент с таким id не найден";
    }

    public LabWork askArgs(String args, ParametrsInput pI, User user){
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
                    pI.inputDiscipline(),
                    user.getLogin()
            );
        } catch (EmptyInputException e) {
            System.out.println("Нет аргумента");
        }
        return null;
    }
}