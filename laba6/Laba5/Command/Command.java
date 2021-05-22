package Command;

import Other.CollectionManager;
import Other.ParametrsInput;

/**
 * интрефейс для реалзиации шаблона Command
 */
public interface Command <T>{
    String getName();
    String getDescription();
    String execute(T args, CollectionManager colman);
}
