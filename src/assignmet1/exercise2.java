package assignmet1;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class exercise2 implements Runnable{
    int num;
    static int largest;
    static int theNumber;

    public exercise2(int num) {
        this.num = num;
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.nanoTime();
        ExecutorService exe = Executors.newFixedThreadPool(500);
        ConcurrentLinkedDeque<Integer> que = new ConcurrentLinkedDeque<>();

        for (int i = 1; i < 100000; i++) {
            que.add(i);
        }

        while (!que.isEmpty()){
            exe.submit(new exercise2(que.poll()));
        }

        exe.shutdown();
        exe.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("The number: " + theNumber);
        System.out.println("Number of divisors: " + largest);

        System.out.println("Time: " + (System.nanoTime()-start)/1000000 + " seconds");



    }

    @Override
    public void run() {

        int tmp = 0;

        for (int i = 1; i <= num; i++) {
            if (num % i == 0) {
                tmp++;
            }

            if (tmp > largest){
                largest = tmp;
                theNumber = i;
            }
        }
    }
}
