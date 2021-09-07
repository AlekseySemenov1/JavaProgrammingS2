package ServerPath;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Set;

public class ResponseWriter extends Thread {
    private DatagramChannel channel;
    private ByteBuffer byteBuffer = ByteBuffer.allocate(10000);

    public ResponseWriter(DatagramChannel channel) {
        this.channel = channel;
    }

    public void writeResponse(Object response, SocketAddress address) {
        try {
            Selector selector = Selector.open();
            SelectionKey keys = channel.register(selector, SelectionKey.OP_WRITE);
            int readyChannels = selector.selectNow();
            if (readyChannels ==0) return;
            Set selectedKeys = selector.selectedKeys();
            if (keys.isWritable()) {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);

                oos.writeObject(response);
                byteBuffer.put(bos.toByteArray());

                bos.flush();
                oos.flush();

                byteBuffer.flip();
                channel.send(byteBuffer, address);
                bos.close();
                oos.close();
                byteBuffer.clear();
                channel.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
