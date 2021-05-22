package Command;

import Other.CollectionManager;
import Other.ParametrsInput;


/**
 * Класс для команды info
 */
public class ComInfo extends CommandAbstract {

    public ComInfo() {
        super("info",
                "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
    }

    @Override
    public String execute(Object args, CollectionManager colMan) {
        return colMan.collectionInfo();
    }
}