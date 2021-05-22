package Command;

import Other.CollectionManager;
import Other.ParametrsInput;

/**
 * Класс для команды remove_head
 */
public class ComRemoveHead extends CommandAbstract {

    public ComRemoveHead() {
        super("remove_head",
                "вывести первый элемент коллекции и удалить его");
    }

    @Override
    public String  execute(Object args, CollectionManager colMan) {
        try {
            if (colMan.getLabCol().isEmpty()) throw new NullPointerException();
            String data = "Первый элемент:" + colMan.getFirst();
            colMan.removeElement(colMan.getFirst());
            return data + "Элемент удален\n";
        } catch (NullPointerException e){
            return "Коллекция пуста";
        }
    }
}

