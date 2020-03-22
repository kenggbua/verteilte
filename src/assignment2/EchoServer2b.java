package assignment2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class EchoServer2b implements Runnable{

    static int portNumber = 49152;
    private Socket clientSocket;

    public EchoServer2b(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(portNumber);

        while (true){
            Socket clientSocker= serverSocket.accept();
            new Thread(new EchoServer2b(clientSocker)).start();
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
                String[] input = inputLine.split(",");
                if(input.length != 3){
                    out.println("Invalid number of parameters");
                }else{
                    try {
                        int number = Integer.parseInt(input[2].trim());
                        if(number <= 0){
                            out.println("invalid number");
                        }
                        switch (input[1].trim()){
                            case "prime":
                                out.println(primes(number));
                                break;
                            case "circle":
                                out.println(calcCircle(number));
                                break;
                            case "sqroot":
                                out.println(calcSqrRoot(number));
                                break;
                            default:
                                out.println("something went wrong");
                        }
                    }catch (NumberFormatException e) {
                        out.println("Invalid number.");
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Integer> primes(int number){
        ArrayList<Integer> primeNumbers = new ArrayList<>();
        if(number == 1){
            return primeNumbers;
        }

        if(number == 2){
            primeNumbers.add(2);
            return primeNumbers;
        }

        primeNumbers.add(2);

        for (int num = 3; num <= number; num = num + 2)
        {
            boolean isPrime = true;
            for (int i=3; i <= num/2; i++)
            {
                if ( num % i == 0)
                {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime)
                primeNumbers.add(num);
        }

        return primeNumbers;
    }

    private double calcCircle(int radius){
        return 2 * Math.PI * radius;
    }

    private double calcSqrRoot(int number){
        return Math.sqrt(number);
    }
}
