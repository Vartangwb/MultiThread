package no10;

import java.util.concurrent.CountDownLatch;

/**
 * Created by twb on 2017/5/17.
 */
public class Videoconference implements Runnable {
    private CountDownLatch controller;

    /**
     * Implement the constructor of the class that initializes the CountDownLatch
     attribute. The Videoconference class will wait for the arrival of the number of
     participants received as a parameter.
     * @param number
     */
    public Videoconference(int number) {
        controller = new CountDownLatch(number);
    }

    public void arrive(String name) {
        System.out.printf("%s has arrived.",name);
        controller.countDown();
        System.out.printf("VideoConference: Waiting for %d participants.\n",controller.getCount());
    }

    @Override
    public void run() {
        System.out.printf("VideoConference: Initialization: %d participants.\n",controller.getCount());
        try {
            controller.await();
            System.out.printf("VideoConference: All the participants have come\n");
            System.out.printf("VideoConference: Let's start...\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
