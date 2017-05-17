package no8;

/**
 * Created by twb on 2017/5/17.
 */
public class Reader implements Runnable {

    private PricesInfo pricesInfo;

    public Reader(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    public void run(){
        for(int i = 0; i < 10; i++){
            System.out.printf("%s: Price 1: %f\n", Thread.currentThread()
                    .getName(), pricesInfo.getPrice1());
            System.out.printf("%s: Price 2: %f\n", Thread.currentThread()
                    .getName(), pricesInfo.getPrice2());
        }
    }
}
