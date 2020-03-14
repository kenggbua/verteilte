import java.util.concurrent.TimeUnit;

public class task1 implements Runnable{
static int count = 0;
static int max = 19;
 static Thread t = new Thread(new task1());


    @Override
    public void run() {
        if(count<max){
            try {
                t = new Thread(new task1());
                t.join();
                System.out.println("This is a thread!" + count);
                t.start();
                count++;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {

        t.start();






    }


}
