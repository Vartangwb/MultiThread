package no2;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

/**
 * Created by tangwb on 2017/5/16.
 */
public class WriterTask implements Runnable{

    public WriterTask(Deque<Event> deque){
        this.deque = deque;
    }

    private Deque<Event> deque;

    public Deque<Event> getDeque() {
        return deque;
    }

    public void setDeque(Deque<Event> deque) {
        this.deque = deque;
    }

    @Override
    public void run(){
        for(int i = 0; i < 100; i++){
            Event event = new Event();
            event.setDate(new Date());
            event.setEvent("The thread" + Thread.currentThread().getId() + " has generated a event");
            deque.addFirst(event);

            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
