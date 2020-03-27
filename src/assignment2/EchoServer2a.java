package assignment2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer2a implements Runnable{

   static int portNumber = 49152;
   private Socket clientSocket;

    public EchoServer2a(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(portNumber);
        ExecutorService exe = Executors.newFixedThreadPool(50);

        while (true){
            Socket clientSocker= serverSocket.accept();
            exe.submit(new Thread(new EchoServer2a(clientSocker)));
        }

    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println("Client connected on port: " + portNumber);
            String inputLine;
            while((inputLine = in.readLine()) != null){
                System.out.println("Received message: " + inputLine + " from " + clientSocket.toString());
                out.println(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
