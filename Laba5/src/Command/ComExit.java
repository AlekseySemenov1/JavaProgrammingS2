package Command;

/**
 * Класс для команды exit
 */
public class ComExit extends CommandAbstract{
    public ComExit() {
        super("exit", "выход без сохранения в файл");
    }
    @Override
    public void execute(String args) {
    }
}
