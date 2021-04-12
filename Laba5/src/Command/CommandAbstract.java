package Command;

/**
 * Абстрактные класс для всех команд
 */
abstract class CommandAbstract implements Command {
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
