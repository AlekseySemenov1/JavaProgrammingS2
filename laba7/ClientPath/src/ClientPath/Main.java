package ClientPath;

import Command.Command;
import Other.CommandCreator;
import Other.ParametrsInput;

import java.net.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            SocketAddress address = new InetSocketAddress("localhost", 10070);
            DatagramSocket socket = new DatagramSocket();
            ResponseReader resReader = new ResponseReader(socket);
            RequestWriter writer = new RequestWriter(socket, address, resReader);
            ParametrsInput parametrsInput = new ParametrsInput();
            CommandCreator creator = new CommandCreator();
            ArrayList<Command> commandsList = creator.createCommands();
            CommandReader reader = new CommandReader(parametrsInput, commandsList, writer);

            AuthorizationManager manager = new AuthorizationManager(writer, reader);
            manager.enter();
        } catch (SocketException e) {
            System.out.println("Проблема у сервера");
        }
    }
}