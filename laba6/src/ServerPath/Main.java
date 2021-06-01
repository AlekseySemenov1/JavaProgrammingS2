package ServerPath;

import Command.Command;
import Other.CollectionLoader;
import Other.CollectionManager;
import Other.CommandCreator;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.time.ZonedDateTime;
import java.util.ArrayList;

/**
 * Main класс приложения.
 * Создает все необходимые объекты
 */

public class Main {
    public static void main(String[] args) throws IOException {
        CollectionLoader loader = new CollectionLoader();
        CollectionManager manager = new CollectionManager(ZonedDateTime.now(), loader);
        CommandCreator creator = new CommandCreator();
        ArrayList<Command> commandsList = creator.createCommands();

        InetSocketAddress address = new InetSocketAddress(10070);
        DatagramChannel channel = DatagramChannel.open();
        channel.configureBlocking(false);

        ResponseWriter resWriter = new ResponseWriter(channel);
        CommandReader comReader = new CommandReader(manager, commandsList, resWriter);
        RequestReader reqReader = new RequestReader(channel, address, comReader);

        reqReader.start();
    }
}
