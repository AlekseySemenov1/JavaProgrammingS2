package Command;


import CollectionElements.User;
import Other.CollectionManager;

/**
 * Класс для команды print_field_descending_difficulty
 */
public class ComFieldDifficulty extends CommandAbstract {

    public ComFieldDifficulty() {
        super("print_field_descending_difficulty","вывести значения поля difficulty всех элементов в порядке убывания");
    }

    @Override
    public String execute(Object args, CollectionManager colMan, User user) {
        try {
            if (colMan.getLabCol().isEmpty()) throw new NullPointerException();
            return colMan.printFieldDifficulty();
        } catch (NullPointerException e){
            return "Коллекция пуста";
        }
    }
}
