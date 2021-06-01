package ServerPath;

import Messages.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ResponseWriter {
    private DatagramChannel channel;
    private ByteBuffer byteBuffer = ByteBuffer.allocate(10000);

    public ResponseWriter(DatagramChannel channel) {
        this.channel = channel;
    }

        public void writeResponse(Response response, SocketAddress address) {
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
