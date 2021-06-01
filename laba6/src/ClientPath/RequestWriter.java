package ClientPath;

import Messages.Request;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;

public class RequestWriter {
    private DatagramSocket socket;
    private SocketAddress address;
    private ResponseReader resReader;

    public RequestWriter(DatagramSocket socket, SocketAddress address, ResponseReader resReader) {
        this.socket = socket;
        this.address = address;
        this.resReader = resReader;
    }

    public boolean writeRequest(Request request) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(request);
            DatagramPacket packet = new DatagramPacket(bos.toByteArray(), bos.toByteArray().length, address);
            bos.flush();
            oos.flush();
            socket.send(packet);
            bos.close();
            oos.close();
            return resReader.readResponse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
