package ServerPath;

import Command.Command;
import Other.CollectionManager;

import java.util.ArrayList;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleReader extends Thread{
    private CollectionManager manager;
    private ArrayList<Command> commandsList;

    public ConsoleReader(CollectionManager manager, ArrayList<Command> commandsList) {
        this.manager = manager;
        this.commandsList = commandsList;
    }

    @Override
    public void run() {
        consoleInput();
    }

    public void consoleInput() {
        try {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String a;
                System.out.println("Введите команду:");
                a = (scanner.nextLine().trim()).toLowerCase();
                executeCommand(a);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Я упаль");
            System.exit(0);
        } catch (IllegalStateException e) {
            System.out.println("Ошибка");
            System.exit(0);
        }
    }

    public void executeCommand(String args) {
        switch (args) {
            case "exit":
                System.exit(0);
                break;
        }
    }
}
