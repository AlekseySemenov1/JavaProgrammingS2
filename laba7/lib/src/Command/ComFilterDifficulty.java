package Command;

import CollectionElements.Difficulty;
import CollectionElements.User;
import Other.CollectionManager;


/**
 * Класс для команды filter_by_difficulty
 */
public class ComFilterDifficulty extends CommandAbstract {

    public ComFilterDifficulty() {
        super("filter_by_difficulty",
                "вывести элементы, значение поля difficulty которых равно заданному");
    }

    @Override
    public String execute(Object args, CollectionManager colMan, User user) {
        try {
            if (colMan.getLabCol().isEmpty()) throw new NullPointerException();
            Difficulty diff = Difficulty.valueOf(((String) args).toUpperCase());
            return colMan.filterDiff(diff);
        } catch (IllegalArgumentException e){
            return "Неправильное значение поля";
        } catch (NullPointerException e){
            return "Коллекция пуста";
        }
    }
}
