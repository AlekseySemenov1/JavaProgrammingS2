package Other;

import CollectionElements.Coordinates;
import CollectionElements.Difficulty;
import CollectionElements.Discipline;
import exceptions.EmptyInputException;
import exceptions.OutOfLimitException;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс для ввода параметров элементов коллекции.
 * Содержит методы для ввода параметров
 */
public class ParametrsInput {
    private Scanner scanner = new Scanner(System.in);

    public String inputName() {
        String name;
        while (true) {
            try {
                System.out.println("Введите имя (имя не может быть пустым)");
                name = scanner.nextLine().trim();
                if (name.equals("")) throw new EmptyInputException();
                break;
            } catch (NoSuchElementException e) {
                System.out.println("Я упаль");
                System.exit(1);
            } catch (EmptyInputException e) {
                System.out.println("Объект должен иметь имя");
            } catch (IllegalStateException e) {
                System.out.println("Неизвестная ошибка");
                System.exit(0);
            }
        }
        return name;
    }

    public Coordinates inputCoordinates() {
        return new Coordinates(inputX(), inputY());
    }

    public double inputX() {
        double x;
        while (true) {
            try {
                System.out.println("Введите координату x (кордината типа double и не должна быть больше 236)");
                x = Double.parseDouble(scanner.nextLine().trim());
                if (x > 236) throw new OutOfLimitException();
                break;
            } catch (NoSuchElementException e) {
                System.out.println("Я упаль");
                System.exit(1);
            } catch (OutOfLimitException e) {
                System.out.println("Координата не должна превышать 236");
            } catch (NumberFormatException e) {
                System.out.println("Координата должна быть числом");
            } catch (NullPointerException | IllegalStateException e) {
                System.out.println("Неизвестная ошибка");
                System.exit(0);
            }
        }
        return x;
    }

    public int inputY() {
        int y;
        while (true) {
            try {
                System.out.println("Введите координату y (кордината типа int и не должна быть больше 988)");
                y = Integer.parseInt(scanner.nextLine().trim());
                if (y > 988) throw new OutOfLimitException();
                break;
            } catch (NoSuchElementException e) {
                System.out.println("Я упаль");
                System.exit(1);
            } catch (OutOfLimitException e) {
                System.out.println("Координата не должна превышать 988");
            } catch (NumberFormatException e) {
                System.out.println("Координата должна быть числом");
            } catch (IllegalStateException e) {
                System.out.println("Неизвестная ошибка");
                System.exit(0);
            }
        }
        return y;
    }

    public float inputMinimalPoint() {
        float min;
        while (true) {
            try {
                System.out.println("Введите минимальное кол-во баллов (тип float, больше нуля)");
                min = Float.parseFloat(scanner.nextLine().trim());
                if (min <= 0) throw new OutOfLimitException();
                break;
            } catch (NoSuchElementException e) {
                System.out.println("Я упаль");
                System.exit(1);
            } catch (OutOfLimitException e) {
                System.out.println("Кол-во баллов должно быть больше нуля");
            } catch (NumberFormatException e) {
                System.out.println("Баллы должны быть числом");
            } catch (NullPointerException | IllegalStateException e) {
                System.out.println("Неизвестная ошибка");
                System.exit(0);
            }
        }
        return min;
    }

    public double inputMaximumPoint() {
        double max;
        while (true) {
            try {
                System.out.println("Введите максимальное кол-во баллов (тип double, больше нуля)");
                max = Double.parseDouble(scanner.nextLine().trim());
                if (max <= 0) throw new OutOfLimitException();
                break;
            } catch (NoSuchElementException e) {
                System.out.println("Я упаль");
                System.exit(1);
            } catch (OutOfLimitException e) {
                System.out.println("Кол-во баллов должно быть больше нуля");
            } catch (NumberFormatException e) {
                System.out.println("Баллы должны быть числом");
            } catch (NullPointerException | IllegalStateException e) {
                System.out.println("Неизвестная ошибка");
                System.exit(0);
            }
        }
        return max;
    }

    public double inputAveragePoint() {
        double p;
        while (true) {
            try {
                System.out.println("Введите среднее кол-во баллов (тип double, больше нуля)");
                p = Double.parseDouble(scanner.nextLine().trim());
                if (p <= 0) throw new OutOfLimitException();
                break;
            } catch (NoSuchElementException e) {
                System.out.println("Я упаль");
                System.exit(1);
            } catch (OutOfLimitException e) {
                System.out.println("Кол-во баллов должно быть больше нуля");
            } catch (NumberFormatException e) {
                System.out.println("Баллы должны быть числом");
            } catch (NullPointerException | IllegalStateException e) {
                System.out.println("Неизвестная ошибка");
                System.exit(0);
            }
        }
        return p;
    }

    public Difficulty inputDifficulty() {
        Difficulty difficulty;
        while (true) {
            try {
                System.out.println("Введите сложность");
                System.out.println("VERY_EASY, HOPELESS, TERRIBLE?");
                difficulty = Difficulty.valueOf(scanner.nextLine().trim().toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Такой сложности нет");
            } catch (NoSuchElementException e) {
                System.out.println("Я упаль");
                System.exit(1);
            } catch (IllegalStateException e) {
                System.out.println("Неизвестная ошибка");
                System.exit(0);
            }
        }
        return difficulty;
    }

    public Discipline inputDiscipline() {
        return new Discipline(inputDisciplineName(), inputPracticeHours(), inputSelfStudyHours());
    }

    public String inputDisciplineName() {
        String name;
        while (true) {
            try {
                System.out.println("Введите название предмета (не может быть пустым)");
                name = scanner.nextLine().trim();
                if (name.equals("")) throw new EmptyInputException();
                break;
            } catch (NoSuchElementException e) {
                System.out.println("Я упаль");
                System.exit(1);
            } catch (EmptyInputException e) {
                System.out.println("Предмету нужно название");
            } catch (IllegalStateException e) {
                System.out.println("Неизвестная ошибка");
                System.exit(0);
            }
        }
        return name;
    }

    public int inputPracticeHours() {
        int y;
        while (true) {
            try {
                System.out.println("Введите кол-во часов практики (тип int, положительное)");
                y = Integer.parseInt(scanner.nextLine().trim());
                if (y <= 0) throw new OutOfLimitException();
                break;
            } catch (NoSuchElementException e) {
                System.out.println("Я упаль");
                System.exit(1);
            } catch (OutOfLimitException e) {
                System.out.println("Кол-во часов не может быть неположительным");
            } catch (NumberFormatException e) {
                System.out.println("Кол-во часов должно быть числом");
            } catch (IllegalStateException e) {
                System.out.println("Неизвестная ошибка");
                System.exit(0);
            }
        }
        return y;
    }

    public long inputSelfStudyHours() {
        long y;
        while (true) {
            try {
                System.out.println("Введите кол-во часов самостоятельного изучения (тип long, положительное)");
                y = Long.parseLong(scanner.nextLine().trim());
                if (y <= 0) throw new OutOfLimitException();
                break;
            } catch (NoSuchElementException e) {
                System.out.println("Я упаль");
                System.exit(1);
            } catch (OutOfLimitException e) {
                System.out.println("Кол-во часов не может быть неположительным");
            } catch (NumberFormatException e) {
                System.out.println("Кол-во часов должно быть числом");
            } catch (IllegalStateException e) {
                System.out.println("Неизвестная ошибка");
                System.exit(0);
            }
        }
        return y;
    }

    public long inputId() {
        long id;
        while(true) {
            try {
                System.out.println("Введите id элемента (тип long)");
                id = Long.parseLong(scanner.nextLine().trim());
                if (id <= 0) throw new OutOfLimitException();
                break;
            } catch (NoSuchElementException e) {
                System.out.println("Я упаль");
                System.exit(1);
            } catch (OutOfLimitException e) {
                System.out.println("Id не может быть неположительным");
            } catch (NumberFormatException e) {
                System.out.println("Id должно быть числом");
            } catch (IllegalStateException e) {
                System.out.println("Неизвестная ошибка");
                System.exit(0);
            }
        }
        return id;
    }

    /**
     * Сеттер для сканера(в зависимости от способа ввода).
     *
     * @param scanner сканер для чтения
     */
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
