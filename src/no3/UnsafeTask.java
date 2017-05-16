package no3;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/5/16.
 */


/**
 * 问题背景：在多线程开发中，经常会遇见在run方法里面调用一个公共的属性的事情，由于每次start都会创建一个线程，因此
 * 线程共享一个属性，当其中任何一个线程更改了这个属性的值，这个属性在下面的使用过程中都会被改变
 * 主要因为所有的线程公用一个属性，有一个线程改变了他的值，导致后面的其实调用的都是一个变量的值，我们
 * 的期望是每个Thread都有自己的属性的值
 */
public class UnsafeTask implements Runnable{


    private Date startDate;
    @Override
    public void run(){
        startDate = new Date();
        System.out.println("thread " + Thread.currentThread().getId() + " " + startDate);
        try {
            TimeUnit.SECONDS.sleep((int)Math.rint(Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread finish " + Thread.currentThread().getId() + " " + startDate);
    }

    public static void main(String[] args) {
        UnsafeTask task = new UnsafeTask();
        for(int i = 0;i<10;i++) {
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
