package no5;

/**
 * Created by twb on 2017/5/17.
 */
public class Bank implements Runnable{

    private Account account;

    public Bank(Account account) {
        this.account = account;
    }


    @Override
    public void run(){
        for(int i = 0;i < 100;i++){
            account.subAmount(1000);
        }
    }
}
