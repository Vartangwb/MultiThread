package no9;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by twb on 2017/5/17.
 */
public class Buffer {

    private LinkedList<String> buffer;
    private int maxSize;
    private ReentrantLock lock;
    private Condition lines;
    private Condition space;
    private boolean pendingLines;

    public Buffer(int maxSize) {
        this.maxSize = maxSize;
        buffer = new LinkedList<String>();
        lock = new ReentrantLock();
        lines = lock.newCondition();
        space = lock.newCondition();
        pendingLines = true;
    }

    public void insert(String line){
        lock.lock();
        try{
            while(buffer.size() == maxSize){
                space.await();
            }
            buffer.add(line);
            System.out.printf("%s: Inserted Line: %d\n", Thread.currentThread().getName(),buffer.size());
            lines.signalAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    /**
     * Implement the get() method. It returns the first string stored in the buffer. First, it
     gets the control of the lock. When it has it, it checks if there are lines in the buffer.
     If the buffer is empty, it calls the await() method in the lines condition to wait
     for lines in the buffer. This thread will be woken up when another thread calls the
     signal() or signalAll() method in the lines condition. When it happens, the
     method gets the first line in the buffer, calls the signalAll() method over the
     space condition and returns String.
     * @return
     */
    public String get() {
        String line = null;
        lock.lock();
        try {
            while((buffer.size() == 0) && (hasPendingLines())) {
                lines.await();
            }
            if(hasPendingLines()) {
                line = buffer.poll();
                System.out.printf("%s: Line Readed: %d\n",Thread.currentThread().getName(),buffer.size());
                space.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
        return line;
    }

    /**
     * Implement the setPendingLines() method that establishes the value of the
     attribute pendingLines. It will be called by the producer when it has no more lines
     to produce.
     * @param pendingLines
     */
    public void setPendingLines(boolean pendingLines) {
        this.pendingLines=pendingLines;
    }

    /**
     * Implement the hasPendingLines() method. It returns true if there are more lines
     to be processed, or false otherwise.
     * @return
     */
    public boolean hasPendingLines() {
        return pendingLines || buffer.size() > 0;
    }

}
