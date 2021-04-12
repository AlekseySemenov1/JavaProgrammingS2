package Command;

import Other.CollectionManager;

/**
 * Класс для команды remove_head
 */
public class ComRemoveHead extends CommandAbstract {
    private CollectionManager colMan;

    public ComRemoveHead(CollectionManager colMan) {
        super("remove_head",
              "вывести первый элемент коллекции и удалить его");
        this.colMan = colMan;
    }

    @Override
    public void execute(String args) {
        System.out.println("Первый элемент:" + colMan.getFirst());
        colMan.removeElement(colMan.getFirst());
        System.out.println("Элемент удален\n");
    }
}

