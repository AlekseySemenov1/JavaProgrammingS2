package Command;


import Other.CollectionManager;
import Other.ParametrsInput;

/**
 * Класс для команды remove_by_id
 */
public class ComRemoveById extends CommandAbstract {

    public ComRemoveById() {
        super("remove_by_id",
                "удалить элемент из коллекции по его id");
    }

    @Override
    public String execute(Object args, CollectionManager colMan) {
        try {
            long id = Long.parseLong((String) args);
            if(!colMan.removeById(id)) throw new NullPointerException();
            return "Элемент удален";
        } catch (NumberFormatException e){
           return "Неверный формат id";
        } catch (NullPointerException e) {
            return "Элемент с таким id не найден";
        }
    }

}
