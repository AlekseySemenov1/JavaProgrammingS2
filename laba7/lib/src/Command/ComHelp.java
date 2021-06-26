package Command;

import CollectionElements.User;
import Other.CollectionManager;

/**
 * Класс для команды help
 */
public class ComHelp extends CommandAbstract {
    public ComHelp() {
        super("help", "вывести справку по доступным командам");
    }

    @Override
    public String execute(Object args, CollectionManager colMan, User user) {
        return null;
    }
}

