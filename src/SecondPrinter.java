import java.util.Random;

/**
 * Created by Andrew on 12/16/2016.
 */
class SecondPrinter extends Thread {
    private String message;

    SecondPrinter(String msg) {
        this.message = msg;
    }

    Random random = new Random();

    @Override
    public void run() {
        System.out.println("Second : " + message);
        try {
            Thread.sleep(random.nextInt(5000));
        } catch (InterruptedException e) {
            e.getLocalizedMessage();
        }
    }
}
