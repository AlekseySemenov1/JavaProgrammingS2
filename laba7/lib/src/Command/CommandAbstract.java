package Command;

import Other.ParametrsInput;

import java.io.Serializable;

/**
 * Абстрактные класс для всех команд
 */
abstract class CommandAbstract<T> implements Command, Serializable {
    private String name;
    private String description;
    private int executeStatus = -1;

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

    public int getExecuteStatus() {
        return executeStatus;
    }

    public void setExecuteStatus(int executeStatus) {
        this.executeStatus = executeStatus;
    }
}
