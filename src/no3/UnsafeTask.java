package no3;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/5/16.
 */


/**
 * ���ⱳ�����ڶ��߳̿����У�������������run�����������һ�����������Ե����飬����ÿ��start���ᴴ��һ���̣߳����
 * �̹߳���һ�����ԣ��������κ�һ���̸߳�����������Ե�ֵ����������������ʹ�ù����ж��ᱻ�ı�
 * ��Ҫ��Ϊ���е��̹߳���һ�����ԣ���һ���̸߳ı�������ֵ�����º������ʵ���õĶ���һ��������ֵ������
 * ��������ÿ��Thread�����Լ������Ե�ֵ
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
