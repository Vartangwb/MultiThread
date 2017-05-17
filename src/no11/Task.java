package no11;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by twb on 2017/5/17.
 */
public class Task implements Runnable {
    // store the creation date of the task
    private Date initDate;
    // store the name of the task
    private String name;

    public Task(String name) {
        this.initDate = new Date();
        this.name = name;
    }

    @Override
    public void run() {
        System.out.printf("%s: Task %s: Created on: %s\n", Thread
                .currentThread().getName(), name, initDate);
        System.out.printf("%s: Task %s: Started on: %s\n", Thread
                .currentThread().getName(), name, new Date());

        try {
            Long duration = (long) (Math.random() * 10);
            System.out.printf("%s: Task %s: Doing a task during %d seconds\n",
                    Thread.currentThread().getName(), name, duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("%s: Task %s: Finished on: %s\n",Thread.
                currentThread().getName(),name,new Date());
    }
}
