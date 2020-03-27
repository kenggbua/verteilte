package assignment2cont;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;

public class Client {

    private DatagramSocket socket;
    private InetAddress address;

    public Client() throws UnknownHostException, SocketException {
        socket = new DatagramSocket();
        address = InetAddress.getByName("localhost");
    }

    public String sendEcho(String msg) throws IOException {
        byte[] send = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(send, send.length, address, 4445);
        socket.send(packet);
        send = new byte[256];
        packet = new DatagramPacket(send, send.length);
        socket.receive(packet);

        return new String(packet.getData(),0,packet.getLength()).trim();
    }

    public void close() {
        socket.close();
    }
}
