package Other;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс для ввода и обработки команд
 */
public class CommandReader {
    private CommandInvoker cominv;
    private ParametrsInput pI;
    private ArrayList<String> fileList = new ArrayList<>();

    public CommandReader(CommandInvoker cominv, ParametrsInput pI) {
        this.cominv = cominv;
        this.pI = pI;
    }

    /**
     * Метод для ручного ввода и обработки команд
     */
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
        } catch (IllegalStateException e){
            System.out.println("Ошибка");
            System.exit(0);
        }
    }

    /**
     * Метод для ввода и обработки команд из скрипта
     * @param script набор команд
     */
    public void scriptinput(String script) {
        try {
            for (String scriptname: fileList)
                if (script.equals(scriptname)) throw new RecursionException();
            fileList.add(script);
            Scanner scanner = new Scanner(new FileReader(script));
            while (scanner.hasNextLine()) {
                String[] a;
                System.out.println("Введите команду:");
                a = (scanner.nextLine().trim() + " ").split(" ", 2);
                pI.setScanner(scanner);
                a[0] = a[0].toLowerCase();
                a[1] = a[1].trim();
                System.out.println(a[0]);
                executeCommand(a);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Я упаль");
            System.exit(0);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IllegalStateException e){
            System.out.println("Ошибка");
        }
        fileList.remove(script);
    }

    /**
     * Метод для вызова соответствующей команды
     * @param args строка с командой
     */
    public void executeCommand(String[] args) {
        switch (args[0]) {
            case "add":
                cominv.comAdd(args[1]);
                break;

            case "clear":
                cominv.comClear(args[1]);
                break;

            case "count_less_than_discipline":
                cominv.comCountLessDiscipline(args[1]);
                break;

            case "print_field_descending_difficulty":
                cominv.comFieldDifficulty(args[1]);
                break;

            case "filter_by_difficulty":
                cominv.comFilterDifficulty(args[1]);
                break;

            case "help":
                cominv.comHelp();
                break;

            case "history":
                cominv.showHistory();
                break;

            case "info":
                cominv.comInfo(args[1]);
                break;

            case "remove_by_id":
                cominv.comRemoveById(args[1]);
                break;

            case "remove_head":
                cominv.comRemoveHead(args[1]);
                break;

            case "remove_lower":
                cominv.comRemoveLower(args[1]);
                break;

            case "save":
                cominv.comSave(args[1]);
                break;

            case "execute_script":
                cominv.comScript();
                scriptinput(args[1]);
                break;

            case "show":
                cominv.comShow(args[1]);
                break;

            case "update_by_id":
                cominv.comUpdateById(args[1]);
                break;

            case "exit":
                System.exit(0);

            case "":
                System.out.println("Введена пустая строка");
                break;

            default:
                System.out.println("Нет такой команды, введите команду help, чтобы просмотреть список доступных команд");
        }
    }
}
