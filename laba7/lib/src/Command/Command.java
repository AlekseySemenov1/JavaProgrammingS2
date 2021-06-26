package Command;

import CollectionElements.User;
import Other.CollectionManager;

/**
 * интрефейс для реалзиации шаблона Command
 */
public interface Command <T>{
    String getName();
    String getDescription();
    String execute(T args, CollectionManager colman, User user);
}
