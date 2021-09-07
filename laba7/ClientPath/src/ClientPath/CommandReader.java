package ClientPath;

import CollectionElements.Discipline;
import CollectionElements.LabWork;
import CollectionElements.User;
import Command.*;
import Messages.CommandRequest;
import Other.ParametrsInput;
import exceptions.EmptyInputException;
import exceptions.RecursionException;

import javax.naming.NoPermissionException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CommandReader {
    private ParametrsInput pI;
    private ArrayList<Command> commandsList;
    private RequestWriter writer;
    private ArrayList<String> fileList = new ArrayList<>();
    private User user;

    public CommandReader(ParametrsInput pI, ArrayList<Command> commandsList, RequestWriter writer) {
        this.pI = pI;
        this.commandsList = commandsList;
        this.writer = writer;
    }

    public void userinput() {
        try {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String[] a;
                System.out.println("Введите команду:");
                a = (scanner.nextLine().trim() + " ").split(" ", 2);
                pI.setScanner(scanner);
                a[0] = a[0].toLowerCase();
                a[1] = a[1].trim();
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

    public void scriptinput(String script) {
        try {
            if (script.equals("")) throw new EmptyInputException();
            for (String scriptname: fileList)
                if (script.equals(scriptname)) throw new RecursionException();
            fileList.add(script);
            File file = new File(script);
            if (!file.canExecute()&& file.exists()) throw new NoPermissionException();
            Scanner scanner = new Scanner(new FileReader(file));
            while (scanner.hasNextLine()) {
                String[] a;
                System.out.println("Введите команду:");
                a = (scanner.nextLine().trim() + " ").split(" ", 2);
                pI.setScanner(scanner);
                a[0] = a[0].toLowerCase();
                a[1] = a[1].trim();
                System.out.println(a[0] + ' ' + a[1]);
                executeCommand(a);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Я упаль");
            System.exit(0);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IllegalStateException e){
            System.out.println("Ошибка");
        } catch (RecursionException e) {
            System.out.println("Alarm: Зациклились");
        } catch (NoPermissionException e) {
            System.out.println("Нет прав на выполнение файла");
        } catch (EmptyInputException e) {
            System.out.println("Пустой ввод");
        }
        fileList.remove(script);
    }

    public void executeCommand(String[] args) {
        try{
            switch (args[0]) {
                case "add":
                    LabWork laba = ((ComAdd) commandsList.get(0)).askArgs(pI, user);
                    writer.writeCommandRequest(new CommandRequest(commandsList.get(0), laba, user));
                    break;

                case "clear":
                    writer.writeCommandRequest(new CommandRequest(commandsList.get(1), user));
                    break;

                case "count_less_than_discipline":
                    Discipline discipline = ((ComCountLessDiscipline) commandsList.get(2)).askArgs(pI);
                    writer.writeCommandRequest(new CommandRequest(commandsList.get(2), discipline, user));
                    break;

                case "print_field_descending_difficulty":
                    writer.writeCommandRequest(new CommandRequest(commandsList.get(5), user));
                    break;

                case "filter_by_difficulty":
                    writer.writeCommandRequest(new CommandRequest(commandsList.get(3), args[1], user));
                    break;

                case "help":
                    writer.writeCommandRequest(new CommandRequest(commandsList.get(6), user));
                    break;

                case "history":
                    writer.writeCommandRequest(new CommandRequest(commandsList.get(7), user));
                    break;

                case "info":
                    writer.writeCommandRequest(new CommandRequest(commandsList.get(8), user));
                    break;

                case "remove_by_id":
                    if (args[1].equals("")) throw new EmptyInputException();
                    writer.writeCommandRequest(new CommandRequest(commandsList.get(9), args[1], user));
                    break;

                case "remove_head":
                    writer.writeCommandRequest(new CommandRequest(commandsList.get(10), user));
                    break;

                case "remove_lower":
                    System.out.println("Введите элемент для сравнения, сравнение производится по id");
                    LabWork laba1 = ((ComRemoveLower) commandsList.get(11)).askArgs(pI, user);
                    writer.writeCommandRequest(new CommandRequest(commandsList.get(11), laba1, user));
                    break;

                case "execute_script":
                    writer.writeCommandRequest(new CommandRequest(commandsList.get(12), user));
                    scriptinput(args[1]);
                    break;

                case "show":
                    writer.writeCommandRequest(new CommandRequest(commandsList.get(13), user));
                    break;

                case "update_by_id":
                    if (args[1].equals("")) throw new EmptyInputException();
                    if (writer.writeCommandRequest(new CommandRequest(commandsList.get(14), args[1], user)) == 1) {
                        LabWork laba2 = ((ComUpdateById) commandsList.get(14)).askArgs(args[1], pI, user);
                        laba2.setId(Long.parseLong(args[1]));
                        writer.writeCommandRequest(new CommandRequest(commandsList.get(14), laba2, user));
                    }
                    break;

                case "exit":
                    writer.writeCommandRequest(new CommandRequest(commandsList.get(4), user));
                    System.exit(0);

                case "":
                    System.out.println("Введена пустая строка");
                    break;

                default:
                    System.out.println("Нет такой команды, введите команду help, чтобы просмотреть список доступных команд");
            }
        } catch (EmptyInputException e) {
            System.out.println("Недопустимое значение аргумента");
        }
    }

    public void setUser(User user) {
        this.user = user;
    }
}
