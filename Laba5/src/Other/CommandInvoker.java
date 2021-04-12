package Other;

import Command.Command;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Класс для создания объектов всех классов команд.
 * Класс Invoker для шаблона Command.
 * Вызывает методы execute у объектов всех команд
 */
public class CommandInvoker {
    private LinkedList<String> history = new LinkedList<>();
    private ArrayList<Command> helpList = new ArrayList<>();

    private Command comAdd;
    private Command comClear;
    private Command comCountLessDiscipline;
    private Command comExit;
    private Command comFieldDifficulty;
    private Command comFilterDifficulty;
    private Command comHelp;
    private Command comHistory;
    private Command comInfo;
    private Command comRemoveById;
    private Command comRemoveHead;
    private Command comRemoveLower;
    private Command comSave;
    private Command comScript;
    private Command comShow;
    private Command comUpdateById;

    public CommandInvoker(Command comAdd, Command comClear, Command comCountLessDiscipline, Command comFilterDifficulty,
                          Command comExit, Command comFieldDifficulty, Command comHelp, Command comHistory,
                          Command comInfo, Command comRemoveById, Command comRemoveHead, Command comRemoveLower,
                          Command comSave, Command comScript, Command comShow, Command comUpdateById) {
        this.comAdd = comAdd;
        this.comClear = comClear;
        this.comCountLessDiscipline = comCountLessDiscipline;
        this.comExit = comExit;
        this.comFieldDifficulty = comFieldDifficulty;
        this.comHelp = comHelp;
        this.comHistory = comHistory;
        this.comInfo = comInfo;
        this.comRemoveById = comRemoveById;
        this.comRemoveHead = comRemoveHead;
        this.comRemoveLower = comRemoveLower;
        this.comSave = comSave;
        this.comScript = comScript;
        this.comShow = comShow;
        this.comUpdateById = comUpdateById;
        this.comFilterDifficulty = comFilterDifficulty;

        helpList.add(comAdd);
        helpList.add(comClear);
        helpList.add(comCountLessDiscipline);
        helpList.add(comExit);
        helpList.add(comFieldDifficulty);
        helpList.add(comHelp);
        helpList.add(comHistory);
        helpList.add(comInfo);
        helpList.add(comRemoveById);
        helpList.add(comRemoveHead);
        helpList.add(comRemoveLower);
        helpList.add(comSave);
        helpList.add(comScript);
        helpList.add(comShow);
        helpList.add(comUpdateById);
        helpList.add(comFilterDifficulty);
    }

    /**
     * Выводит историю команд
     */
    public void showHistory() {
        System.out.println("Последние введенные команды:");
        for (String q : history)
            System.out.println(q);
        addToHistory(comHelp.getName());
    }

    /**
     * Добавляет исполняемую команду в историю
     * @param name имя команды
     */
    public void addToHistory(String name) {
        if (history.size() < 10)
            history.addLast(name);
        else {
            history.remove(history.getFirst());
            history.addLast(name);
        }
    }

    /**
     * Выводит справку по доступным командам
     */
    public void comHelp() {
        for (Command com : helpList) {
            System.out.println(com.getName() + " : " + com.getDescription());
        }
        addToHistory(comHelp.getName());
    }

    public void comAdd(String args) {
        comAdd.execute(args);
        addToHistory(comAdd.getName());
    }

    public void comClear(String args) {
        comClear.execute(args);
        addToHistory(comClear.getName());
    }

    public void comCountLessDiscipline(String args) {
        comCountLessDiscipline.execute(args);
        addToHistory(comCountLessDiscipline.getName());
    }

    public void comExit(String args) {
        comExit.execute(args);
        addToHistory(comExit.getName());
    }

    public void comFieldDifficulty(String args) {
        comFieldDifficulty.execute(args);
        addToHistory(comFieldDifficulty.getName());
    }

    public void comInfo(String args) {
        comInfo.execute(args);
        addToHistory(comInfo.getName());
    }

    public void comRemoveById(String args) {
        comRemoveById.execute(args);
        addToHistory(comRemoveById.getName());
    }

    public void comRemoveHead(String args) {
        comRemoveHead.execute(args);
        addToHistory(comRemoveHead.getName());
    }

    public void comRemoveLower(String args) {
        comRemoveLower.execute(args);
        addToHistory(comRemoveLower.getName());
    }

    public void comSave(String args) {
        comSave.execute(args);
        addToHistory(comSave.getName());
    }

    public void comScript() {
        comScript.execute("");
        addToHistory(comScript.getName());
    }

    public void comShow(String args) {
        comShow.execute(args);
        addToHistory(comShow.getName());
    }

    public void comUpdateById(String args) {
        comUpdateById.execute(args);
        addToHistory(comUpdateById.getName());
    }

    public void comFilterDifficulty(String args) {
        comFilterDifficulty.execute(args);
        addToHistory(comFilterDifficulty.getName());
    }
}
