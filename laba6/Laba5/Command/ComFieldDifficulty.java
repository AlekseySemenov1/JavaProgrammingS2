package Command;


import Other.CollectionManager;
import Other.ParametrsInput;

/**
 * Класс для команды print_field_descending_difficulty
 */
public class ComFieldDifficulty extends CommandAbstract {

    public ComFieldDifficulty() {
        super("print_field_descending_difficulty","вывести значения поля difficulty всех элементов в порядке убывания");
    }

    @Override
    public String execute(Object args, CollectionManager colMan) {
        try {
            if (colMan.getLabCol().isEmpty()) throw new NullPointerException();
            return colMan.printFieldDifficulty();
        } catch (NullPointerException e){
            return "Коллекция пуста";
        }
    }
}
