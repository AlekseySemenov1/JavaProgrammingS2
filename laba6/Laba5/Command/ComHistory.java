package Command;

import Other.CollectionManager;
import Other.ParametrsInput;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Класс для команды history
 */
public class ComHistory extends CommandAbstract {
    private LinkedList<String> history = new LinkedList<>();

    public ComHistory() {
        super("history", "вывести последние 10 команд (без их аргументов)");
    }

    @Override
    public String execute(Object args, CollectionManager colMan) {
        try {
            if (history.isEmpty()) throw new NullPointerException();
            return history.stream().reduce("", (h1, h2) -> h1 += h2 + "\n");
        } catch (NullPointerException e){
            return "История пуста";
        }
    }

    public void addToHistory(String name) {
        if (history.size() < 10)
            history.addLast(name);
        else {
            history.remove(history.getFirst());
            history.addLast(name);
        }
    }
}
