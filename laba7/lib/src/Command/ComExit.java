package Command;

import CollectionElements.User;
import Other.CollectionManager;

/**
 * Класс для команды exit
 */
public class ComExit extends CommandAbstract{
    public ComExit() {
        super("exit", "выход из приложения с сохранением коллекции в файл");
    }


    @Override
    public String execute(Object args, CollectionManager colMan, User user) {
        return "q";
    }

}
