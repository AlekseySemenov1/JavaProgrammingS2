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

    public boolean readResponse() {
        try {
            byte[] message = new byte[100000];
            DatagramPacket packet1 = new DatagramPacket(message, message.length);
            socket.setSoTimeout(1000);
            socket.receive(packet1);

            ByteArrayInputStream bis = new ByteArrayInputStream(message);
            ObjectInputStream ois = new ObjectInputStream(bis);
            Response response = (Response) ois.readObject();

            if (response.getMessage().equals("Нет элемента с таким id")){
                System.out.println(response.getMessage());
                return false;
            }
            System.out.println(response.getMessage());
            return true;
        } catch (SocketTimeoutException e){
            System.out.println("Превышено время ожидани ответа от сервера/сервер недоступен");
        } catch (EOFException e){
            System.out.println("Слишком большой объем данных");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
