package assignment2cont;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ServerC {


    public static void main(String[] args) {
        ArrayList<ServerMainC> threads = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            try {
                ServerMainC thread = new ServerMainC(4321, InetAddress.getByName("229.0.0.0"), "TS-"+i);
                thread.start();
                threads.add(thread);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        for (ServerMainC thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
