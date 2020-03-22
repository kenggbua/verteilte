package assignmet1;


public class exercise1threads implements Runnable{
    static int number;
    static int largest;
    static int theNumber;


    public static void main(String[] args) {
        long start = System.nanoTime();

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new exercise1threads());
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("The number: " + theNumber);
        System.out.println("Number of divisors: " + largest);

        System.out.println("Time: " + (System.nanoTime()-start)/1000000 + " milliseconds");


    }

    @Override
    public void run() {
        int number = numbers();
        dividers(number);
    }


    public int dividers(int end){

        int begin;
        if (end == 10000) {
            begin = 1;
        } else {
            begin = end - 10000;
        }


        for (int i = begin; i <= end; i++) {
            int tmp = 0;
            for (int j = 1; j <= i/2; j++) {
                if(i % j == 0){
                    tmp++;
                }
            }
            bigger(tmp,i);
        }

        return largest;
    }

    public synchronized static int numbers(){
        number = number + 10000;
        return number;
    }

    public synchronized void bigger(int tmp, int number){
        if (tmp > largest){
            largest = tmp;
            theNumber = number;
        }
    }


}
