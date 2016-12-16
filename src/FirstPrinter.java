import java.util.Random;

/**
 * Created by Andrew on 12/16/2016.
 */

class FirstPrinter extends Thread {
    private String message;

    FirstPrinter(String msg) {
        this.message = msg;
    }

    Random random = new Random();

    @Override
    public void run() {
        int currentTime = random.nextInt(5000);
        if (currentTime > 5000) {
            System.out.println("More then 5 seconds!");
        }
        System.out.println("First : " + message);
        try {
            Thread.sleep(currentTime);
        } catch (InterruptedException e) {
            e.getLocalizedMessage();
        }
    }

}
