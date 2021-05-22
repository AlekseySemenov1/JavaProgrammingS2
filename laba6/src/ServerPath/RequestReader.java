package ServerPath;

import Command.Command;
import Messages.Request;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class RequestReader {
    private DatagramChannel channel;
    private InetSocketAddress address;
    private ByteBuffer byteBuffer = ByteBuffer.allocate(10000);
    private CommandReader comReader;

    public RequestReader(DatagramChannel channel, InetSocketAddress address, CommandReader comReader) {
        this.channel = channel;
        this.address = address;
        this.comReader = comReader;
    }

    public void start(){
        try {
            channel.bind(address);
            while (true) {
                try {
                    SocketAddress address = channel.receive(byteBuffer);
                    ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(byteBuffer.array()));
                    Request request = (Request) ois.readObject();
                    byteBuffer.clear();
                    comReader.executeCommand(request.getCommand(), request.getArg(), address);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
