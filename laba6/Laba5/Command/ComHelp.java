package Command;

import Other.CollectionManager;
import Other.ParametrsInput;

/**
 * Класс для команды help
 */
public class ComHelp extends CommandAbstract {
    public ComHelp() {
        super("help", "вывести справку по доступным командам");
    }

    @Override
    public String execute(Object args, CollectionManager colMan) {
        return null;
    }
}

