package Command;


import CollectionElements.User;
import Other.CollectionManager;

/**
 * Класс для команды remove_by_id
 */
public class ComRemoveById extends CommandAbstract {

    public ComRemoveById() {
        super("remove_by_id",
                "удалить элемент из коллекции по его id");
    }

    @Override
    public String execute(Object args, CollectionManager colMan, User user) {
        try {
            setExecuteStatus(-1);
            long id = Long.parseLong((String) args);
            setExecuteStatus(colMan.removeById(id, user));
            if (getExecuteStatus() == 1)
                return "Элемент удалён";
            else if (getExecuteStatus() == 2)
                return "Данный элемент принадлежит не вам, вы не можете его изменять";
            else throw new NullPointerException();
        } catch (NumberFormatException e){
           return "Неверный формат id";
        } catch (NullPointerException e) {
            return "Элемент с таким id не найден";
        }
    }
}
