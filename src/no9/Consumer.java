package no9;

import java.util.Random;

/**
 * Created by twb on 2017/5/17.
 */
public class Consumer implements Runnable {

    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    /**
     * Implement the run() method. While the buffer has pending lines, it tries to get one
     and process it.
     */
    @Override
    public void run() {
        while(buffer.hasPendingLines()) {
            String line = buffer.get();
            processLine(line);
        }
    }

    /**
     * Implement the auxiliary method processLine(). It only sleeps for 10 milliseconds
     to simulate some kind of processing with the line.
     * @param line
     */
    private void processLine(String line) {
        Random random = new Random();
        try {
            Thread.sleep(random.nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FileMock fileMock = new FileMock(100, 10);
        Buffer buffer = new Buffer(20);

        Producer producer = new Producer(fileMock, buffer);
        Thread threadProducer = new Thread(producer, "Producer");

        Consumer consumers[] = new Consumer[3];
        Thread threadConsumers[] = new Thread[3];
        for(int i = 0; i < 3; i++) {
            consumers[i] = new Consumer(buffer);
            threadConsumers[i] = new Thread(consumers[i], "consumer " + i);
        }

        threadProducer.start();
        for(int i = 0; i < 3; i++) {
            threadConsumers[i].start();
        }
    }
}
