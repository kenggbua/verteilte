package assignment2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) {
        int portNumber = 49152;
        String hostName = "localhost";
        try{
            Socket echoSocket = new Socket(hostName,portNumber);
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            String userInput;
            while ((userInput = stdIn.readLine()) != null){
                out.println(echoSocket.getInetAddress() + " " + echoSocket.getPort() + ", " +  userInput);
                System.out.println("echo: " + in.readLine());
            }
        } catch (UnknownHostException e) {
            System.err.println("Host not known: " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Could not connect to: " + hostName);
            System.exit(1);
        }
    }
}
