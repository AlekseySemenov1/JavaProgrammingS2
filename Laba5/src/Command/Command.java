package Command;

/**
 * интрефейс для реалзиации шаблона Command
 */
public interface Command {
    String getName();
    String getDescription();
    void execute(String args);
}
