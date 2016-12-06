package com.clock.krith.analogclock;

import android.os.CountDownTimer;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by krith on 06/12/16.
 */

public class Clock {

    ClockView mView;
    int second;
    int minute;
    int hour;
    CountDownTimer timer;

    public Clock(ClockView mView) {
        this.mView = mView;
    }

    public void startTimer() throws InterruptedException {
        Calendar calendar = Calendar.getInstance();
        second = calendar.get(Calendar.SECOND);
        minute = calendar.get(Calendar.MINUTE);
        hour = calendar.get(Calendar.HOUR);
        rotateHands(true, true, true);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                boolean rSecond = true;
                boolean rMinute = false;
                boolean rHour = false;
                second = (second + 1) % Constants.totalIntervals;
                if (second == 0) {
                    minute = (minute + 1) % Constants.totalIntervals;
                    rMinute = true;
                    if (minute == 0) {
                        hour = (hour + 1) % Constants.totalHours;
                        rHour = true;
                    }
                }
                rotateHands(rSecond, rMinute, rHour);
            }
        }, 0, Constants.secondInMillis);
    }

    public void rotateHands(boolean rotateSecond, boolean rotateMinute, boolean rotateHour) {
        if (rotateSecond) {
            float rotateDegree = ((second * Constants.secondDegree) % Constants.fullDegree) - Constants.secondHandOffset;
            mView.rotateSecond(rotateDegree);
        }
        if (rotateMinute) {
            float rotateDegree = ((minute * Constants.secondDegree) % Constants.fullDegree) - Constants.minuteHandOffset;
            mView.rotateMinute(rotateDegree);
        }
        if (rotateHour) {
            float rotateDegree = ((hour * Constants.hourDegree) % Constants.fullDegree) - Constants.hourHandOffset;
            mView.rotateHour(rotateDegree);
        }
    }
}
