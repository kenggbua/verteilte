package assignment2cont;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Date;

public class ServerMainC extends Thread{
    private int port;
    private InetAddress address;
    private String name;

    public ServerMainC(int port, InetAddress address, String name) {
        this.port = port;
        this.address = address;
        this.name = name;
    }

    @Override
    public void run() {
        MulticastSocket socket = null;
        try {
            socket = new MulticastSocket(port);
            socket.joinGroup(address);
            byte[] data = new byte[1024];
            while (true) {
                DatagramPacket receivedPacket = new DatagramPacket(data, data.length);
                socket.receive(receivedPacket);
                System.out.println(name + " received: " + new String(data));
                long currentMillis = new Date().getTime();
                String response = name + "#" + currentMillis + "#";
                DatagramPacket sendPacket = new DatagramPacket(response.getBytes(), response.getBytes().length, receivedPacket.getAddress(), receivedPacket.getPort());
                socket.send(sendPacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
