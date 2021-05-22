package Command;

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
    public String execute(Object args, CollectionManager colMan) {
        try {
            if (colMan.getLabCol().isEmpty()) throw new NullPointerException();
            colMan.colClear();
            return "Коллекция очищенна\n";
        } catch (NullPointerException e){
            return "Коллекция пуста\n";
        }
    }
}
