package Command;

import Other.CollectionManager;

/**
 * Класс для команды clear
 */
public class ComClear extends CommandAbstract{
    private CollectionManager colMan;

    public ComClear(CollectionManager colman) {
        super("сlear", "Очистить коллекцию");
        this.colMan = colMan;
    }

    @Override
    public void execute(String args) {
        colMan.colClear();
        System.out.println("Коллекция очищенна\n");
    }
}
