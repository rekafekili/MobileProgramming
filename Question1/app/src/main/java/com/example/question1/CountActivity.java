package com.example.question1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class CountActivity extends AppCompatActivity {
    private TextView introTextView;
    private Button stopButton;
    private TextView countTextView;

    private int count = 0;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);

        initView();
        String name = getIntent().getStringExtra("name");
        int speed = getIntent().getIntExtra("speed", 1);

        String introString = String.format("%s님 반갑습니다. 속도는 = %d", name, speed);
        introTextView.setText(introString);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if(count < 20) {
                    count++;
                    countTextView.setText(Integer.toString(count));
                } else {
                    finishCount();
                }
            }
        };

        timer = new Timer();
        timer.schedule(timerTask, 0, speed);
    }

    private void initView() {
        introTextView = findViewById(R.id.count_intro_textview);
        stopButton = findViewById(R.id.count_stop_button);
        countTextView = findViewById(R.id.count_number_textview);

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishCount();
            }
        });
    }

    private void finishCount() {
        timer.cancel();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("count", Integer.toString(count));
        setResult(RESULT_OK, intent);
        finish();
    }
}