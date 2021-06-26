package Command;

import CollectionElements.User;
import Other.CollectionManager;


/**
 * Класс для команды info
 */
public class ComInfo extends CommandAbstract {

    public ComInfo() {
        super("info",
                "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
    }

    @Override
    public String execute(Object args, CollectionManager colMan, User user) {
        return colMan.collectionInfo();
    }
}