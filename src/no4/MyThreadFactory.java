package no4;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadFactory;

/**
 * Created by Administrator on 2017/5/16.
 */
public class MyThreadFactory implements ThreadFactory{
    private int counter;
    private String name;
    private List<String> stats;

    public MyThreadFactory(String name){
        counter = 0;
        this.name = name;
        stats = new ArrayList<>();
    }

    @Override
    public Thread newThread(Runnable run){
        Thread t = new Thread(run, name + "-Thread-" + counter);
        counter++;
        stats.add(String.format("Created thread %d with name %s on%s\n" ,t.getId() ,t.getName() ,new Date()));
        return t;
    }
    public String getStas() {
        StringBuffer buffer = new StringBuffer();
        Iterator<String> it = stats.iterator();
        while(it.hasNext()) {
            buffer.append(it.next());
            buffer.append("\n");
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        MyThreadFactory factory = new MyThreadFactory("MyThreadFactory");

        Runnable task = new Runnable() {
            @Override
            public void run() {
            }
        };
        Thread thread = null;
        for(int i = 0; i < 10; i++) {
            thread = factory.newThread(task);
            thread.start();
        }
        System.out.printf("Factory stats:\n");
        System.out.printf("%s\n",factory.getStas());


    }


}
