package ServerPath;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RequestReader extends Thread{
    private DatagramChannel channel;
    private InetSocketAddress address;
    private ByteBuffer byteBuffer = ByteBuffer.allocate(10000);
    private CommandReader comReader;
    private AuthorizationManager authoManager;

    public RequestReader(DatagramChannel channel, InetSocketAddress address, CommandReader comReader, AuthorizationManager authoManager) {
        this.channel = channel;
        this.address = address;
        this.comReader = comReader;
        this.authoManager = authoManager;
    }

    @Override
    public void run() {
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
                    ExecutorService service = Executors.newFixedThreadPool(2);
                    while (keyIterator.hasNext()) {
                        keyIterator.next();
                        if (keys.isReadable()) {
                            SocketAddress address = channel.receive(byteBuffer);
                            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(byteBuffer.array()));
                            Object object = ois.readObject();
                            ois.close();
                            byteBuffer.clear();
                            service.submit(new RequestExecutor(object,comReader,authoManager, address));
                        }
                        keyIterator.remove();
                    }
                    service.shutdown();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
