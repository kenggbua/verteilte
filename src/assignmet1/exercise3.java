package assignmet1;

import java.util.concurrent.*;

public class exercise3 implements Runnable{
    int num;

    static LinkedBlockingQueue<TwoNumbers> block = new LinkedBlockingQueue<>();
    static int blockLength = 0;

    public exercise3(int num) {
        this.num = num;
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.nanoTime();
        ExecutorService exe = Executors.newFixedThreadPool(50);
        ConcurrentLinkedQueue<Integer> que = new ConcurrentLinkedQueue<>();
        TwoNumbers t1;
        TwoNumbers t2 = new TwoNumbers(1,1);


        for (int i = 1; i < 100000; i++) {
            que.add(i);
        }

        while (!que.isEmpty()){
            exe.submit(new exercise3(que.poll()));
        }

        exe.shutdown();
        exe.awaitTermination(1, TimeUnit.DAYS);


        blockLength = block.size();

        for (int i = 1; i <= blockLength; i++) {

            t1 = block.poll();

            if(t1 != null && t1.largest > t2.largest){
                t2 = t1;
            }
        }

        System.out.println("The number: " + t2.theNumber);
        System.out.println("Number of divisors: " + t2.largest);
        System.out.println("Time: " + (System.nanoTime()-start)/1000000 + " milliseconds");

    }

    @Override
    public void run() {

        int tmp = 0;

        for (int i = 1; i <= num/2; i++) {
            if (num % i == 0) {
                tmp++;
            }

        }

        block.add(new TwoNumbers(tmp+1,num));

    }


}
