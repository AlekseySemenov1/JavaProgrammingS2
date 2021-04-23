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
        try {
            long id = Long.parseLong(args);
            if(!colMan.removeById(id)) throw new NullPointerException();
            System.out.println("Элемент удален");
        } catch (NumberFormatException e){
            System.out.println("Неверный формат id");
        } catch (NullPointerException e){
            System.out.println("Элемент с таким id не найден");
        }
}
