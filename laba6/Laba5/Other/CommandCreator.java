package Other;

import Command.*;

import java.util.ArrayList;

public class CommandCreator {

    public CommandCreator() {
    }

    public ArrayList<Command> createCommands() {
        ArrayList<Command> commands = new ArrayList();
        commands.add(new ComAdd());
        commands.add(new ComClear());
        commands.add(new ComCountLessDiscipline());
        commands.add(new ComFilterDifficulty());
        commands.add(new ComExit());
        commands.add(new ComFieldDifficulty());
        commands.add(new ComHelp());
        commands.add(new ComHistory());
        commands.add(new ComInfo());
        commands.add(new ComRemoveById());
        commands.add(new ComRemoveHead());
        commands.add(new ComRemoveLower());
        commands.add(new ComScript());
        commands.add(new ComShow());
        commands.add(new ComUpdateById());
        return commands;
    }
}
