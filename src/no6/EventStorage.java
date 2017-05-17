package no6;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by twb on 2017/5/17.
 */
public class EventStorage {

    private int maxSize;
    private List<Date> storage;
    public EventStorage() {
        maxSize = 10;
        storage = new LinkedList<Date>();
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public List<Date> getStorage() {
        return storage;
    }

    public void setStorage(List<Date> storage) {
        this.storage = storage;
    }

    public synchronized void set(){
        while(storage.size() == maxSize){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        storage.add(new Date());
        System.out.printf("set: %d\n",storage.size());
        notifyAll();
    }


    public synchronized void get(){
        while(storage.size() == 0){
            try{
                wait();

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.printf("Get: %d:%s",storage.size(),((LinkedList<?>)storage).poll());
        notifyAll();
    }
}
