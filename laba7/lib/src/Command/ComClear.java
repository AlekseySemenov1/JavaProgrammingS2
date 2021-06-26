package Command;

import CollectionElements.User;
import Other.CollectionManager;
import Other.ParametrsInput;

/**
 * Класс для команды clear
 */
public class ComClear extends CommandAbstract{

    public ComClear() {
        super("clear", "Очистить коллекцию");
    }

    @Override
    public String execute(Object args, CollectionManager colMan, User user) {
        try {
            setExecuteStatus(-1);
            if (colMan.getLabCol().isEmpty()) throw new NullPointerException();
            return colMan.colClear(user);
        } catch (NullPointerException e){
            return "Коллекция пуста\n";
        }
    }
}
