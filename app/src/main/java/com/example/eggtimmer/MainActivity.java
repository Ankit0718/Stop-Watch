package com.example.eggtimmer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView timerTextView;
    SeekBar timerSeekBar;
    Boolean counterIsActive = false;
    Button startButton;
    CountDownTimer countDownTimer;
    public void clicked(View view) {
        if (counterIsActive) {
            timerTextView.setText("0:30");
            timerSeekBar.setProgress(30);
            timerSeekBar.setEnabled(true);
            countDownTimer.cancel();
            startButton.setText("Go!");
            counterIsActive = false;

        } else {
            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            startButton.setText("Stop");

            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 100) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTime((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
                    mediaPlayer.start();

                }
            }.start();
        }
    }
        public void updateTime(int progress){
            int minutes = progress/60;
            int seconds = progress - (minutes*60);
            String secondTime = Integer.toString(seconds);
            if (seconds <=9){
                secondTime = "0"+secondTime;
            }
            timerTextView.setText(Integer.toString(minutes)+":"+ secondTime);
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = (SeekBar) findViewById(R.id.timerSeekBar);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        startButton = findViewById(R.id.startButton);
        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTime(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}