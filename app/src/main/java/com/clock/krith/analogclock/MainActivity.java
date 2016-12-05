package com.clock.krith.analogclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.iv_clock)
    ImageView ivClock;

    @BindView(R.id.iv_second)
    ImageView ivSecond;

    @BindView(R.id.iv_minute)
    ImageView ivMinute;

    @BindView(R.id.iv_hour)
    ImageView ivHour;

    static AnimationSet animSet;
    CountDownTimer timer;
    int second;
    int minute;
    int hour;

    int full_degree = 360;
    float offset1 = 45.0f;
    float offset2 = 90.0f;
    float offset3 = 135.0f;
    long upper_value = 1000000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setTimer(offset1, offset2, offset3);
        timeChangeListener();
    }

    private void timeChangeListener() {
        Calendar calendar = Calendar.getInstance();
        second = calendar.get(Calendar.SECOND);
        minute = calendar.get(Calendar.MINUTE);
        hour = calendar.get(Calendar.HOUR);
        rotateSecond(second);
        rotateMinute(minute);
        rotateHour(hour);
        timer = new CountDownTimer(upper_value, 1000) {
            @Override
            public void onTick(long l) {
                second = (second + 1) % 60;
                rotateSecond(second);
                if (second == 0) {
                    minute = (minute + 1) % 60;
                    rotateMinute(minute);
                    if (minute == 0) {
                        hour = (hour + 1) % 12;
                        rotateHour(hour);
                    }
                }
                if (upper_value < 1000)
                    upper_value = upper_value * 1000;
            }

            @Override
            public void onFinish() {

            }
        };
        timer.start();
    }

    private void setTimer(float t1, float t2, float t3) {
        ivSecond.setRotation(-t1);
        ivMinute.setRotation(-t2);
        ivHour.setRotation(-t3);
    }

    private void rotateSecond(int value) {
        float rotate_value = (value * 6) % full_degree;
        rotate_value = rotate_value - offset1;
        ivSecond.setRotation(rotate_value);
    }

    private void rotateMinute(int value) {
        float rotate_value = (value * 6) % full_degree;
        rotate_value = rotate_value - offset2;
        ivMinute.setRotation(rotate_value);
    }

    private void rotateHour(int value) {
        float rotate_value = (value * 30) % full_degree;
        rotate_value = rotate_value - offset3;
        ivHour.setRotation(rotate_value);
    }

    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
    }
}
