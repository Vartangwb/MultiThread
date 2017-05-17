package no7;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by twb on 2017/5/17.
 */
public class PrintQueue {

    private final Lock queueLock = new ReentrantLock();

    public void printJob(Object document){
        queueLock.lock();
        Long duration = (long)(Math.random() * 10000);
        System.out.println(Thread.currentThread().getName() + ":PrintQueue: Printing a Job during "+(duration/1000)+" seconds");
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            queueLock.unlock();
        }
    }

    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();
        Thread thread[] = new Thread[10];
        for(int i = 0; i < 10; i++) {
            thread[i] = new Thread(new Job(printQueue), "Thread " + i);
        }
        for(int i = 0; i < 10; i++) {
            thread[i].start();
        }
    }
}
