package Command;

/**
 * Класс для команды history
 */
public class ComHistory extends CommandAbstract{
    public ComHistory() {
        super("history", "вывести последние 10 команд (без их аргументов)");
    }

    @Override
    public void execute(String args) {
    }
}
