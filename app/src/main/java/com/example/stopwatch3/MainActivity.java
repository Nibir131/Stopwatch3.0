package com.example.stopwatch3;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView stopwatchText;
    private boolean isRunning;
    private int seconds = 0;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            updateTimer();
            handler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stopwatchText = findViewById(R.id.stopwatchText);
    }

    public void startTimer(View view) {
        if (!isRunning) {
            isRunning = true;
            handler.post(runnable);
        }
    }

    public void stopTimer(View view) {
        isRunning = false;
        handler.removeCallbacks(runnable);
    }

    public void resetTimer(View view) {
        isRunning = false;
        handler.removeCallbacks(runnable);
        seconds = 0;
        updateTimer();
    }

    private void updateTimer() {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;

        String time = String.format("%02d:%02d:%02d", hours, minutes, secs);
        stopwatchText.setText(time);

        if (isRunning) {
            seconds++;
        }
    }
}
