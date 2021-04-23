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
        try {
            if (colMan.getLabCol().isEmpty()) throw new NullPointerException();
            colMan.colClear();
            System.out.println("Коллекция очищенна\n");
        } catch (NullPointerException e){
            System.out.println("Коллекция пуста");
        }
    }
}
