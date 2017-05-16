package no1;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by tangwb on 2017/5/16.
 */
/**
 当在一个方法里面调用其他的线程的时候，如果使用了类似thread1.join(),这样的话，这个调用的线程
 就开始一直等待thread1这个线程返回，什么时候thread1这个线程的run方法运行完了返回了，这个当前的主线程才会继
 续向下运行。当然，join还有两个参数方法，这个参数的意思就是，首先等待这个线程调用完，比如
 thread1.sleep(1000)，这样主线程就会等待thread1运行，直到thread1运行完返回或者当前主线程等待超过1秒钟就会不
 理这个thread1线程继续向下执行。
 */

public class MultiThread implements Runnable{
    @Override
    public void run(){
        System.out.println("begin trans"  + new Date());
        try{
            TimeUnit.SECONDS.sleep(4);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("begin trans1" + new Date());
    }

    public static void main(String[] args) {
        MultiThread multiThread = new MultiThread();
        Thread thread1 = new Thread(multiThread, "multiThread");
        thread1.start();
        try{
            thread1.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(new Date());
    }
}
