package ClientPath;

import CollectionElements.User;
import Exceptions.NotEqualsPasswords;
import Messages.EnterRequest;
import Messages.RegisterRequest;
import Messages.SaltRequest;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class AuthorizationManager {
    private Scanner scanner = new Scanner(System.in);
    private RequestWriter writer;
    private CommandReader comReader;

    public AuthorizationManager(RequestWriter writer, CommandReader comReader) {
        this.writer = writer;
        this.comReader = comReader;
    }

    public void enter() {
        try {
            while (true) {
                System.out.println("Введите команду \"register\", чтобы зарегистрироваться, команду \"login\", чтобы войти или команду \"exit\", чтобы завершить работу приложения");
                String a = scanner.next();
                if (selectEnterType(a)) break;
            }
            comReader.userinput();
        } catch (NoSuchElementException e){
            System.out.println("Я упал");
        }
    }

    private boolean authorization() {
        try {
            while (writer.connectToServer() != 0) {
                System.out.println("Введите логин");
                String login = scanner.next().trim();
                writer.writeSaltRequest(new SaltRequest(login));
                System.out.println("Введите пароль");
                String password = scanner.next().trim();
                String pepper = "6n8oibq";
                String salt = writer.getSalt();
                MessageDigest ms = MessageDigest.getInstance("MD5");
                byte[] hash = ms.digest((pepper + password + salt).getBytes(StandardCharsets.UTF_8));
                if (writer.writeEnterRequest(new EnterRequest(new User(login, hash))) == 1) {
                    comReader.setUser(new User(login, hash));
                    return true;
                }
                return false;
            }
            return false;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Критическая ошибка");
            return false;
        }
    }

    private boolean register() {
        try {
            String login = "";
            String password = "";
            while (writer.connectToServer() != 0) {
                System.out.println("Введите логин");
                login = scanner.next().trim();
                System.out.println("Введите пароль");
                password = scanner.next().trim();
                System.out.println("Введите пароль еще раз");
                try {
                    if (!password.equals(scanner.next().trim())) throw new NotEqualsPasswords();
                    String salt = String.valueOf((long) (Math.random() * 1000000000L));
                    String pepper = "6n8oibq";
                    MessageDigest ms = MessageDigest.getInstance("MD5");
                    byte[] hash = ms.digest((pepper + password + salt).getBytes(StandardCharsets.UTF_8));
                    if (writer.writeRegisterRequest(new RegisterRequest(new User(login, hash, salt))) == 1) {
                        comReader.setUser(new User(login, hash));
                        return true;
                    }
                    return false;
                } catch (NotEqualsPasswords notEqualsPasswords) {
                    System.out.println("Пароли не совпадают");
                }
            }
            return false;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Критическая ошибка");
            return false;
        }
    }

    private boolean selectEnterType(String a) {
        switch (a) {
            case "login":
                return authorization();
            case "register":
                return register();
            case "exit":
                System.exit(0);
            default:
                System.out.println("Пройдите регистрацию или авторизируйтесь, чтобы работать с базой данных");
                return false;
        }
    }
}
