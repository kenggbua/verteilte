package assignment2cont;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Arrays;

public class FileServerRun implements Runnable{

    private Socket client;
    private File dir;

    public FileServerRun(Socket client, File dir) {
        this.client = client;
        this.dir = dir;
    }

    @Override
    public void run() {

        try {
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            String input;

            while ((input = in.readLine()) != null){
                String[] input2 = input.split(" ");

                if(input2.length == 1){
                    if(input2[0].equalsIgnoreCase("list")){
                        String[] names = dir.list();
                        out.writeUTF(String.join(", ", Arrays.asList(names)));
                    }else{
                        out.writeUTF("wrong input");
                    }
                }

                if(input2.length == 2){
                    if(input2[0].equalsIgnoreCase("get")){
                        if(new File(dir,input2[1]).exists()){
                            File test = new File(dir,input2[1]);
                            out.writeUTF(input2[1]);
                            out.writeInt((int) test.length());
                            out.write(Files.readAllBytes(test.toPath()));
                        }
                    }
                }else{
                    out.writeUTF("error");
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
