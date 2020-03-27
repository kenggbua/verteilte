package assignment2cont;

import java.io.IOException;

public class  ClientMain {


    public static void main(String[] args) throws IOException {
        Client client = new Client();
        String test = client.sendEcho("");
        System.out.println(test);
    }
}
