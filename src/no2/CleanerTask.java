package no2;

import java.util.Date;
import java.util.Deque;

/**
 * Created by Administrator on 2017/5/16.
 */
public class CleanerTask extends Thread {

    private Deque<Event> deque;
    public CleanerTask(Deque<Event> deque){
        this.deque = deque;
        setDaemon(true);
    }

    @Override
    public void run(){
        while(true){
            Date date = new Date();
            clean(date);
        }
    }

    public void clean(Date date){
        long diff = 0;
        boolean delete = false;
        if(deque.size() == 0){
            return;
        }

        do{
            Event e = deque.getLast();
            diff = date.getTime() - e.getDate().getTime();
            if(diff>10000){
                System.out.println("cleaner" + e.getEvent());
                deque.removeLast();
                delete = true;
            }
        }while (diff > 10000);
        if(delete){
            System.out.println("cleaner : the size of the deque " + deque.size());
        }
    }
}
