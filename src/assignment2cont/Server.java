package assignment2cont;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Server extends Thread{
    private DatagramSocket socket;
    private byte[] buf = new byte[256];
    ArrayList<String> list = new ArrayList<>();

    public Server() throws SocketException {
        socket = new DatagramSocket(4445);
    }

    public void run() {

        list.add("this is a test");
        list.add("another test");
        list.add("another one");
        list.add("another one2");
        list.add("another one3");
        list.add("another one4");
        list.add("another one5");
        list.add("another one6");
        list.add("another one7");
        list.add("another one8");
        boolean running = true;

        while (running) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }

            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            String received = new String(packet.getData(), 0, packet.getLength());


            if (received.equals("")) {
                Random rn = new Random();
                int random = rn.nextInt(11);
                System.out.println(random);
                System.out.println(list.get(random));
                packet = new DatagramPacket(list.get(random).getBytes(), list.get(random).length(), address, port);

                System.out.println(Arrays.toString(packet.getData()));
                running = false;
                try {
                    socket.send(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        socket.close();
    }
}
