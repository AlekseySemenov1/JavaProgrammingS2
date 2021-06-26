package ServerPath;


import CollectionElements.*;
import Command.Command;
import Other.CollectionManager;
import Other.CommandCreator;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main класс приложения.
 * Создает все необходимые объекты
 */

public class Main {
    public static void main(String[] args) throws IOException, SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите логин для подключения к серверу");
        String [] data = new String[2];
        data[0]= scanner.next();
        System.out.println("Введите пароль для подключения к серверу");
        data[1] = scanner.next();

        DataBaseConnector dataBaseConnector = new DataBaseConnector("jdbc:postgresql://pg:5432/studs", data[0], data[1]);
        Connection connection = dataBaseConnector.ConnectToDataBase();
        Validator validator = new Validator();
        LoaderFromBD loader = new LoaderFromBD(connection, validator);

        CollectionManager manager = new CollectionManager(ZonedDateTime.now(), loader.loadCollectionFromBD());
        manager.sortByCoordinates();
        CommandCreator creator = new CommandCreator();
        ArrayList<Command> commandsList = creator.createCommands();

        InetSocketAddress address = new InetSocketAddress(10070);
        DatagramChannel channel = DatagramChannel.open();
        channel.configureBlocking(false);

        ResponseWriter resWriter = new ResponseWriter(channel);
        CommandReader comReader = new CommandReader(manager, commandsList, resWriter, dataBaseConnector);
        AuthorizationManager manager1 = new AuthorizationManager(resWriter, dataBaseConnector, loader.loadUserList());
        RequestReader reqReader = new RequestReader(channel, address, comReader, manager1);


        ConsoleReader conReader = new ConsoleReader(manager,commandsList);
        Thread threadForConsoleInput = conReader;
        Thread threadForRead = reqReader;
        threadForConsoleInput.start();
        threadForRead.start();
    }
}