package no1;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by tangwb on 2017/5/16.
 */
/**
 ����һ��������������������̵߳�ʱ�����ʹ��������thread1.join(),�����Ļ���������õ��߳�
 �Ϳ�ʼһֱ�ȴ�thread1����̷߳��أ�ʲôʱ��thread1����̵߳�run�����������˷����ˣ������ǰ�����̲߳Ż��
 ���������С���Ȼ��join�����������������������������˼���ǣ����ȵȴ�����̵߳����꣬����
 thread1.sleep(1000)���������߳̾ͻ�ȴ�thread1���У�ֱ��thread1�����귵�ػ��ߵ�ǰ���̵߳ȴ�����1���Ӿͻ᲻
 �����thread1�̼߳�������ִ�С�
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
