package com.aolifu.rocketmq.timewheel;

import java.util.concurrent.TimeUnit;
import org.junit.Before;
import org.junit.Test;

public class TimeWheelTest {

    private TimerLauncher launcher;


    @Before
    public void startTimer() {
        launcher = new TimerLauncher();
    }

    @Test
    public void addTask() throws InterruptedException {
        TimerTask task = new TimerTask("I am 3", 3);
        launcher.add(task);

        TimerTask task2 = new TimerTask("I am 10", 10);
        launcher.add(task2);

        TimerTask task3 = new TimerTask("I am 19", 19);
        launcher.add(task3);

        TimerTask task4 = new TimerTask("I am 28", 28);
        launcher.add(task4);

        TimerTask task5 = new TimerTask("I am 82", 52);
        launcher.add(task5);
        TimeUnit.SECONDS.sleep(100);
    }
}
