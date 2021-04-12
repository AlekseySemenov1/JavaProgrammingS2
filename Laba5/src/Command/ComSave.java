package Command;

import Other.CollectionManager;

/**
 * Класс для команды save
 */
public class ComSave extends CommandAbstract {
    private CollectionManager colMan;

    public ComSave(CollectionManager colMan) {
        super("save", "сохранить коллекцию в файл");
        this.colMan = colMan;
    }

    @Override
    public void execute(String args) {
        colMan.save();
    }
}

