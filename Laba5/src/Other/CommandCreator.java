package Other;

import Command.*;

public class CommandCreator {
    private CollectionManager colMan;
    private ParametrsInput parametrsInput;

    public CommandCreator(CollectionManager colMan, ParametrsInput parametrsInput) {
        this.colMan = colMan;
        this.parametrsInput = parametrsInput;
    }

    public CommandInvoker createCommands(){
        return new CommandInvoker(
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
    }
}
