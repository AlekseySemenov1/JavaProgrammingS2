package ClientPath;

import Messages.Response;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ResponseReader {
    private DatagramSocket socket;

    public ResponseReader(DatagramSocket socket) {
        this.socket = socket;
    }

    public void readResponse() {
        try {
            byte[] message = new byte[100000];
            DatagramPacket packet1 = new DatagramPacket(message, message.length);
            socket.receive(packet1);
            ByteArrayInputStream bis = new ByteArrayInputStream(message);
            ObjectInputStream ois = new ObjectInputStream(bis);
            Response response = (Response) ois.readObject();
            System.out.println(response.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
