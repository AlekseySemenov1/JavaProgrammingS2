package Command;

import CollectionElements.LabWork;
import CollectionElements.User;
import Other.CollectionManager;

/**
 * Класс для команды remove_head
 */
public class ComRemoveHead extends CommandAbstract {

    public ComRemoveHead() {
        super("remove_head",
                "вывести первый элемент коллекции и удалить его");
    }

    @Override
    public String execute(Object args, CollectionManager colMan, User user) {
        try {
            setExecuteStatus(-1);
            String data = "Первый элемент:" + args;
            setExecuteStatus(colMan.removeElement((LabWork) args, user));
            if (getExecuteStatus() == 1)
                return data + "Элемент удалён";
            else if (getExecuteStatus() == 2)
                return "Вы не являетесь владельцем элемента";
        } catch (NullPointerException e) {
            return "Коллекция пуста";
        }
        return "ERROR";
    }

    public LabWork findElement(CollectionManager colMan){
        if (colMan.getLabCol().isEmpty()) throw new NullPointerException();
        return colMan.getFirst();
    }
}

