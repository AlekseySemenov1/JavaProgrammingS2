package ClientPath;

import CollectionElements.Discipline;
import CollectionElements.LabWork;
import Command.*;
import Messages.Request;
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
        switch (args[0]) {
            case "add":
                LabWork laba = ((ComAdd) commandsList.get(0)).askArgs(pI);
                writer.writeRequest(new Request(commandsList.get(0), laba));
                break;

            case "clear":
                writer.writeRequest(new Request(commandsList.get(1)));
                break;

            case "count_less_than_discipline":
                Discipline discipline = ((ComCountLessDiscipline) commandsList.get(2)).askArgs(pI);
                writer.writeRequest(new Request(commandsList.get(2), discipline));
                break;

            case "print_field_descending_difficulty":
                writer.writeRequest(new Request(commandsList.get(5)));
                break;

            case "filter_by_difficulty":
                writer.writeRequest(new Request(commandsList.get(3), args[1]));
                break;

            case "help":
                writer.writeRequest(new Request(commandsList.get(6)));
                break;

            case "history":
                writer.writeRequest(new Request(commandsList.get(7)));
                break;

            case "info":
                writer.writeRequest(new Request(commandsList.get(8)));
                break;

            case "remove_by_id":
                writer.writeRequest(new Request(commandsList.get(9), args[1]));
                break;

            case "remove_head":
                writer.writeRequest(new Request(commandsList.get(10)));
                break;

            case "remove_lower":
                LabWork laba1 = ((ComRemoveLower) commandsList.get(11)).askArgs(pI);
                writer.writeRequest(new Request(commandsList.get(11)));
                break;

            case "execute_script":
                writer.writeRequest(new Request(commandsList.get(12)));
                scriptinput(args[1]);
                break;

            case "show":
                writer.writeRequest(new Request(commandsList.get(13)));
                break;

            case "update_by_id":
                if (writer.writeRequest(new Request(commandsList.get(14), args[1])) == true) {
                    LabWork laba2 = ((ComUpdateById) commandsList.get(14)).askArgs(args[1], pI);
                    laba2.setId(Long.parseLong(args[1]));
                    writer.writeRequest(new Request(commandsList.get(14), laba2));
                }
                break;

            case "exit":
                writer.writeRequest(new Request(commandsList.get(4)));
                System.exit(0);

            case "":
                System.out.println("Введена пустая строка");
                break;

            default:
                System.out.println("Нет такой команды, введите команду help, чтобы просмотреть список доступных команд");
        }
    }
}
