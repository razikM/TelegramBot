package org.groupOne.Services.timer;

import org.groupOne.Controller;

import java.time.LocalTime;
import java.util.Date;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class TimeUpdate {

    private Timer timer;
    private Task task;
    private static final long ONE_HOUR = 3600000L;

    public TimeUpdate(Controller telegramBot) {
        this.timer = new Timer();
        this.task = new Task(telegramBot);

    }

    public void startTimer() {
        int currentMinute = LocalTime.now().getMinute();
        int currentSecond = LocalTime.now().getSecond();

        if(currentMinute != 0 && currentSecond != 0) {
           long delay =  TimeUnit.MINUTES.toMillis(60) - TimeUnit.MINUTES.toMillis(currentMinute) - TimeUnit.SECONDS.toMillis(currentSecond);
            timer.schedule(task, delay, ONE_HOUR);
        }
        else {
            timer.schedule(task, 0, ONE_HOUR);
        }
    }
}
