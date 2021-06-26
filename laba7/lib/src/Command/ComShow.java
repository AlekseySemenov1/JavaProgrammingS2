package Command;

import CollectionElements.User;
import Other.CollectionManager;

/**
 * Класс для команды show
 */
public class ComShow extends CommandAbstract {

    public ComShow() {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
    }

    @Override
    public String execute(Object args, CollectionManager colMan, User user) {
        try {
            if (colMan.getLabCol().isEmpty()) throw new NullPointerException();
            return colMan.showCollection();
        } catch (NullPointerException e) {
            return "Коллекция пуста";
        }
    }
}