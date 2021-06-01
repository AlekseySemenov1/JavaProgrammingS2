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

    public void start() {
        try {
            Selector selector = Selector.open();
            SelectionKey keys = channel.register(selector, SelectionKey.OP_READ);
            channel.bind(address);
            while (true) {
                try {
                    int readyChannels = selector.selectNow();
                    if (readyChannels == 0) continue;
                    Set selectedKeys = selector.selectedKeys();
                    Iterator keyIterator = selectedKeys.iterator();
                    while (keyIterator.hasNext()) {
                        keyIterator.next();
                        if (keys.isReadable()) {
                            SocketAddress address = channel.receive(byteBuffer);
                            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(byteBuffer.array()));
                            Request request = (Request) ois.readObject();
                            ois.close();
                            byteBuffer.clear();
                            comReader.executeCommand(request.getCommand(), request.getArg(), address);
                        }
                        keyIterator.remove();
                    }
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
