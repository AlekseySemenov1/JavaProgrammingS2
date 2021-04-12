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
        Difficulty diff = Difficulty.valueOf(args.toUpperCase());
        colMan.filterDiff(diff);
    }
}
