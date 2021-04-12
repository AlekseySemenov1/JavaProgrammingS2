package Command;

/**
 * Класс для команды execute_script
 */
public class ComScript extends CommandAbstract {
    public ComScript() {
        super("execute_script",
           "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в " +
                   "таком же виде, в котором их вводит пользователь в интерактивном режиме");
    }

    @Override
    public void execute(String args) {

    }
}

