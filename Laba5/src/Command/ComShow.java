package Command;


import Other.CollectionManager;

/**
 * Класс для команды show
 */
public class ComShow extends CommandAbstract {
    private CollectionManager colMan;

    public ComShow(CollectionManager colMan) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.colMan = colMan;
    }

    @Override
    public void execute(String args) {
        colMan.showCollection();
    }
}