package Command;

import Other.CollectionManager;

/**
 * Класс для команды exit
 */
public class ComExit extends CommandAbstract{
    public ComExit() {
        super("exit", "выход из приложения с сохранением коллекции в файл");
    }


    @Override
    public String execute(Object args, CollectionManager colMan) {
        return colMan.save();
    }

}
