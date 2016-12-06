package com.clock.krith.analogclock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClockView extends AppCompatActivity implements ClockContract {

    @BindView(R.id.iv_clock)
    ImageView ivClock;

    @BindView(R.id.iv_second)
    ImageView ivSecond;

    @BindView(R.id.iv_minute)
    ImageView ivMinute;

    @BindView(R.id.iv_hour)
    ImageView ivHour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        try {
            Clock clock = new Clock(this);
            clock.startTimer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rotateSecond(float rotateDegree) {
        ivSecond.setRotation(rotateDegree);
    }

    @Override
    public void rotateMinute(float rotateDegree) {
        ivMinute.setRotation(rotateDegree);
    }

    @Override
    public void rotateHour(float rotateDegree) {
        ivHour.setRotation(rotateDegree);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
