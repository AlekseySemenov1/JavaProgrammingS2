package Other;

import Command.*;
import java.time.ZonedDateTime;

/**
 * Main класс приложения.
 * Создает все необходимые объекты
 */
public class Main {
    public static void main(String[] args) {
        ParametrsInput parametrsInput = new ParametrsInput();
        CollectionLoader collectionLoader = new CollectionLoader();
        CollectionManager colMan= new CollectionManager(ZonedDateTime.now(), collectionLoader);
        CommandCreator comCreat = new CommandCreator(colMan, parametrsInput);
        CommandReader commandReader = new CommandReader(comCreat.createCommands(), parametrsInput);

        commandReader.userinput();
    }
}
