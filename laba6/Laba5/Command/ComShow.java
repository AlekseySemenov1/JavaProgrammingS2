package Command;


import Other.CollectionManager;
import Other.ParametrsInput;

/**
 * Класс для команды show
 */
public class ComShow extends CommandAbstract {

    public ComShow() {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
    }

    @Override
    public String execute(Object args, CollectionManager colMan) {
        return colMan.showCollection();
    }
}