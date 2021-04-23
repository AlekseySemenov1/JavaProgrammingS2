package Command;

import CollectionElements.Difficulty;
import Other.CollectionManager;


/**
 * Класс для команды filter_by_difficulty
 */
public class ComFilterDifficulty extends CommandAbstract {
    private CollectionManager colMan;

    public ComFilterDifficulty(CollectionManager colMan) {
        super("filter_by_difficulty difficulty",
              "вывести элементы, значение поля difficulty которых равно заданному");
        this.colMan = colMan;
    }

    @Override
    public void execute(String args) {
        try {
            if (colMan.getLabCol().isEmpty()) throw new NullPointerException();
            Difficulty diff = Difficulty.valueOf(args.toUpperCase());
            colMan.filterDiff(diff);
        } catch (IllegalArgumentException e){
            System.out.println("Неправильное значение поля");
        } catch (NullPointerException e){
            System.out.println("Коллекция пуста");
        }
    }
}
