package ServerPath;

import Command.Command;
import Other.CollectionLoader;
import Other.CollectionManager;
import Other.CommandCreator;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main класс приложения.
 * Создает все необходимые объекты
 */

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите логин для подключения к серверу");
        String [] data = new String[2];
        data[0]= scanner.next();
        System.out.println("Введите пароль для подключения к серверу");
        data[1] = scanner.next();

        CollectionLoader loader = new CollectionLoader();
        CollectionManager manager = new CollectionManager(ZonedDateTime.now(), loader);
        CommandCreator creator = new CommandCreator();
        ArrayList<Command> commandsList = creator.createCommands();

        InetSocketAddress address = new InetSocketAddress(10070);
        DatagramChannel channel = DatagramChannel.open();
        channel.configureBlocking(false);

        Loader loader1 = new Loader();
        ResponseWriter resWriter = new ResponseWriter(channel);
        CommandReader comReader = new CommandReader(manager, commandsList, resWriter);
        PasswordChecker passwordChecker = new PasswordChecker(loader1.loadUsersListFromDB(), resWriter);
        RequestReader reqReader = new RequestReader(channel, address, comReader, passwordChecker);

        DataBaseConnector dataBaseConnector = new DataBaseConnector("jdbc:postgresql://localhost:5430/studs", data[0], data[1]);
        reqReader.start();
    }
}