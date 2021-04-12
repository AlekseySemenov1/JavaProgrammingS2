package Command;


import Other.CollectionManager;

/**
 * Класс для команды remove_by_id
 */
public class ComRemoveById extends CommandAbstract {
    private CollectionManager colMan;

    public ComRemoveById(CollectionManager colMan) {
        super("remove_by_id",
              "удалить элемент из коллекции по его id");
        this.colMan = colMan;
    }

    @Override
    public void execute(String args) {
        long id = Long.parseLong(args);
        colMan.removeById(id);
        System.out.println("Элемент удален");
    }
}
