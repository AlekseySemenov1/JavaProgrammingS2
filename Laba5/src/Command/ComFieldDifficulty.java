package Command;


import Other.CollectionManager;

/**
 * Класс для команды print_field_descending_difficulty
 */
public class ComFieldDifficulty extends CommandAbstract {
    private CollectionManager colMan;

    public ComFieldDifficulty(CollectionManager colMan) {
        super("print_field_descending_difficulty","вывести значения поля difficulty всех элементов в порядке убывания");
        this.colMan = colMan;
    }

    @Override
    public void execute(String args) {
        try {
            if (colMan.getLabCol().isEmpty()) throw new NullPointerException();
            colMan.printFieldDifficulty();
            System.out.println();
        } catch (NullPointerException e){
            System.out.println("Коллекция пуста");
        }
    }
}
