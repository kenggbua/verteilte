package assignment2cont;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Date;

public class ClientC {



    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName("229.0.0.0");
            DatagramSocket datagramSocket = new DatagramSocket(4444);
            String request = "REQ#" + 4444 + "#";
            DatagramPacket sendPacket = new DatagramPacket(request.getBytes(), request.getBytes().length, address, 4321);
            datagramSocket.send(sendPacket);
            ArrayList<DatagramPacket> receivedPackets = new ArrayList<>();
            datagramSocket.setSoTimeout(1000);
            long endTime = System.currentTimeMillis() + 1000;
            while (System.currentTimeMillis() < endTime) {
                try {
                    DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
                    datagramSocket.receive(receivePacket);
                    System.out.println("Received: " + new String(receivePacket.getData()));
                    receivedPackets.add(receivePacket);
                } catch (SocketTimeoutException exception) {
                    break;
                }
            }
            long milliSum = 0;
            for (DatagramPacket packet : receivedPackets) {
                String str = new String(packet.getData());
                String[] splitString = str.split("#");
                milliSum = milliSum + Long.parseLong(splitString[1]);
            }
            if (receivedPackets.size() > 0) {
                milliSum = milliSum / receivedPackets.size();
                System.out.println("Average time: " + new Date(milliSum));
            } else {
                System.out.println("No packets received.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
