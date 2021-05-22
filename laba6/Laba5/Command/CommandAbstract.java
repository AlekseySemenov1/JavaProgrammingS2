package Command;

import Other.ParametrsInput;

import java.io.Serializable;

/**
 * Абстрактные класс для всех команд
 */
abstract class CommandAbstract<T> implements Command, Serializable {
    private String name;
    private String description;

    public CommandAbstract(String name, String description){
        this.name = name;
        this.description = description;
    }

    /**
     * @return имя команды
     */
    public String getName() {
        return name;
    }

    /**
     * @return описание команды
     */
    public String getDescription() {
        return description;
    }

}
