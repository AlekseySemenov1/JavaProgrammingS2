package ClientPath;

import Messages.EnterRequest;
import Messages.RegisterRequest;
import Messages.Request;
import Messages.SaltRequest;

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
    private String salt;

    public RequestWriter(DatagramSocket socket, SocketAddress address, ResponseReader resReader) {
        this.socket = socket;
        this.address = address;
        this.resReader = resReader;
    }

    public int writeCommandRequest(Request request) {
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
            resReader.readResponse();
            return resReader.getExecutestatus();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int writeEnterRequest(EnterRequest request){
        try{
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(request);
            DatagramPacket packet = new DatagramPacket(bos.toByteArray(), bos.toByteArray().length, address);
            bos.flush();
            oos.flush();
            socket.send(packet);
            bos.close();
            oos.close();
            resReader.readResponse();
            return resReader.getExecutestatus();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int connectToServer(){
        try{
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject("connect");
            DatagramPacket packet = new DatagramPacket(bos.toByteArray(), bos.toByteArray().length, address);
            bos.flush();
            oos.flush();
            socket.send(packet);
            bos.close();
            oos.close();
            resReader.readResponse();
            return resReader.getExecutestatus();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int writeRegisterRequest(RegisterRequest request){
        try{
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(request);
            DatagramPacket packet = new DatagramPacket(bos.toByteArray(), bos.toByteArray().length, address);
            bos.flush();
            oos.flush();
            socket.send(packet);
            bos.close();
            oos.close();
            resReader.readResponse();
            return resReader.getExecutestatus();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int writeSaltRequest(SaltRequest request){
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
            resReader.readResponse();
            if (resReader.getExecutestatus() == 3)
                salt = resReader.getSalt();
            return resReader.getExecutestatus();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getSalt() {
        return salt;
    }
}
