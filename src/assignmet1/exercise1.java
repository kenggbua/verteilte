package assignmet1;

public class exercise1 {


    public static void main(String[] args) {

        int largest = 0;

        long start = System.nanoTime();


        for (int i = 1; i <= 100000; i++) {
            int tmp = 0;
            for (int j = 1; j <= i/2; j++) {
                if(i % j == 0){
                    tmp++;
                }
            }
            if(tmp > largest){
                largest = tmp + 1;
            }
        }

        System.out.println("largest:" + largest);
        System.out.println((System.nanoTime()-start)/1000000 + " milliseconds");
    }
}
