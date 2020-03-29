package assignment2cont;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;

public class FileClient {


    public static void main(String[] args) {
        int portNumber = 49152;
        String hostName = "localhost";
        try{
            Socket echoSocket = new Socket(hostName,portNumber);
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(),true);
            DataInputStream in = new DataInputStream(echoSocket.getInputStream());
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String userInput;
            while ((userInput = stdIn.readLine()) != null){
                out.println(userInput);
                String response = in.readUTF();
                System.out.println(response);
                int responseSize = in.readInt();
                if(responseSize > 0){
                    System.out.println(responseSize);
                    byte[] resp = new byte[responseSize];
                    in.read(resp);
                    File dest = new File("C:\\Users\\kingo\\Desktop\\test",response);
                    Files.write(dest.toPath(), resp);
                }

            }
    } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } }
