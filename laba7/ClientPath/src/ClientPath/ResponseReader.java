package ClientPath;

import Messages.EnterResponse;
import Messages.Response;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;

public class ResponseReader {
    private DatagramSocket socket;
    private int executestatus;
    private String salt;

    public ResponseReader(DatagramSocket socket) {
        this.socket = socket;
    }

    public void readResponse() {
        try {
            byte[] message = new byte[100000];
            DatagramPacket packet1 = new DatagramPacket(message, message.length);
            socket.setSoTimeout(1000);
            socket.receive(packet1);

            ByteArrayInputStream bis = new ByteArrayInputStream(message);
            ObjectInputStream ois = new ObjectInputStream(bis);
            Object object = ois.readObject();
            if (object instanceof EnterResponse) {
                EnterResponse response = (EnterResponse) object;
                if (response.isEnterstatus() == 3)
                {
                    salt = response.getMessage();
                    executestatus = response.isEnterstatus();
                    return;
                } else if (response.isEnterstatus() != 2)
                    System.out.println(response.getMessage());
                executestatus = response.isEnterstatus();
                return;
            }
            Response response = (Response) object;
            System.out.println(response.getMessage());
            executestatus = response.isExecuteStatus();
            return;
        } catch (SocketTimeoutException e) {
            System.out.println("Превышено время ожидани ответа от сервера/сервер недоступен");
            executestatus = 0;
            return;
        } catch (EOFException e) {
            System.out.println("Слишком большой объем данных");
            executestatus = 0;
            return;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        executestatus = 0;
        return;
    }

    public int getExecutestatus() {
        return executestatus;
    }

    public String getSalt() {
        return salt;
    }
}
