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
        CommandInvoker commandInvoker = new CommandInvoker(
                new ComAdd(colMan, parametrsInput),
                new ComClear(colMan),
                new ComCountLessDiscipline(colMan, parametrsInput),
                new ComFilterDifficulty(colMan),
                new ComExit(),
                new ComFieldDifficulty(colMan),
                new ComHelp(),
                new ComHistory(),
                new ComInfo(colMan),
                new ComRemoveById(colMan),
                new ComRemoveHead(colMan),
                new ComRemoveLower(colMan,parametrsInput),
                new ComSave(colMan),
                new ComScript(),
                new ComShow(colMan),
                new ComUpdateById(colMan, parametrsInput)
        );
        CommandReader commandReader = new CommandReader(commandInvoker, parametrsInput);

        commandReader.userinput();
    }
}
