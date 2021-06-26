package ServerPath;

import Messages.EnterRequest;
import Messages.RegisterRequest;
import Messages.Request;
import Messages.SaltRequest;

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
                    while (keyIterator.hasNext()) {
                        keyIterator.next();
                        if (keys.isReadable()) {
                            SocketAddress address = channel.receive(byteBuffer);
                            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(byteBuffer.array()));
                            Object object = ois.readObject();
                            ois.close();
                            byteBuffer.clear();
                            ExecutorService service = Executors.newFixedThreadPool(2);
                            if (object instanceof Request) {
                                Request request = (Request) object;
                                comReader.executeCommand(request.getCommand(), request.getArg(), address, request.getUser());
                            } else if (object instanceof EnterRequest){
                                EnterRequest request = (EnterRequest) object;
                                authoManager.checkPassword(request.getUser().getLogin(), request.getUser().getPassword(), address);
                            } else if (object instanceof RegisterRequest){
                                RegisterRequest request = (RegisterRequest) object;
                                authoManager.addNewUser(request.getUser(), address);
                            } else if (object.equals("connect")){
                                authoManager.setConnection(address);
                            } else if (object instanceof SaltRequest){
                                SaltRequest request = (SaltRequest) object;
                                authoManager.getSalt(request.getLogin(), address);
                            }
                        }
                        keyIterator.remove();
                    }
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
