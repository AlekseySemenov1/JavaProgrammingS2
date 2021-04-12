package Command;

import Other.CollectionManager;


/**
 * Класс для команды info
 */
public class ComInfo extends CommandAbstract {
    private CollectionManager colMan;

    public ComInfo(CollectionManager colMan) {
        super("info",
              "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        this.colMan = colMan;
    }

    @Override
    public void execute(String args) {
        System.out.println(colMan.collectionInfo());
    }
}