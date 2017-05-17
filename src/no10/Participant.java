package no10;

import java.util.concurrent.TimeUnit;

/**
 * Created by twb on 2017/5/17.
 */
public class Participant implements Runnable {
    private Videoconference videoConference;

    private String name;

    public Participant(Videoconference videoConference, String name) {
        this.videoConference = videoConference;
        this.name = name;
    }


    @Override
    public void run() {
        long duration=(long)(Math.random()*10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        videoConference.arrive(name);
    }

    public static void main(String[] args) {
        //Create a Videoconference object named conference that waits for 10 participants.
        Videoconference videoCOnference = new Videoconference(10);
        Thread threadConference = new Thread(videoCOnference);
        threadConference.start();

        for(int i = 0; i < 10; i++) {
            Participant participant = new Participant(videoCOnference, "participant" + i);
            Thread t = new Thread(participant);
            t.start();
        }
    }
}
