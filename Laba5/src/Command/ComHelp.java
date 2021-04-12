package Command;

/**
 * Класс для команды help
 */
public class ComHelp extends CommandAbstract {
    public ComHelp() {
        super("help", "вывести справку по доступным командам");
    }

    @Override
    public void execute(String args) {
    }
}

