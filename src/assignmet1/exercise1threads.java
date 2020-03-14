package assignmet1;


public class exercise1threads implements Runnable{
    static int number;
    static int largest;
    static int theNumber;


    public static void main(String[] args) {
        long start = System.nanoTime();

        for (int i = 0; i < 5; i++) {
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

        System.out.println("Time: " + (System.nanoTime()-start)/1000000000 + " seconds");


    }

    @Override
    public void run() {
        int number = numbers();
        divider(number);


    }


    public int divider(int range){

        int temp= 1;
        if (range == 20000) {
            temp = range / 20000;
        } else {
            temp = range - 20000;
        }


        for (int i = temp; i <= range; i++) {
            int tmp = 0;
            for (int j = 1; j <= i/2; j++) {
                if(i % j == 0){
                    tmp++;
                }
            }
            if(tmp > largest){
                largest = tmp + 1;
                theNumber = i;
            }
        }

        return largest;
    }

    public synchronized static int numbers(){
        number = number + 20000;
        return number;
    }


}
