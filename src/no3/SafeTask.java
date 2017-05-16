package no3;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/5/16.
 */
public class SafeTask implements Runnable {

    private static ThreadLocal<Date> startDate = new ThreadLocal<Date>(){
        protected Date initialValue(){
            return new Date();
        };
    };

    @Override
    public void run(){
        System.out.println("start thread " + Thread.currentThread().getId()
                + " " + startDate.get());
        try {
            TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread finish " + Thread.currentThread().getId()
                + " " + startDate.get());
    }

    public static void main(String[] args) {
        SafeTask task = new SafeTask();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(task);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
