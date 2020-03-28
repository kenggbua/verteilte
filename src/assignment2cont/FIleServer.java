package assignment2cont;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FIleServer {



    public static void main(String[] args) {



        File file = new File("C:\\Users\\kingo\\Desktop\\test1");




        try {
            ServerSocket server = new ServerSocket(49152);
            while (true){
                Socket client = server.accept();
                new Thread(new FileServerRun(client,file)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }





}
