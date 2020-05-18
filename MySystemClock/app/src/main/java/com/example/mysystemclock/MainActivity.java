package com.example.mysystemclock;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * 정확한 시간이 표시되는 스톱워치를 만들려고 함. ("SystemClock" 사용함)
 */
public class MainActivity extends AppCompatActivity {
    private TextView tvMillis;
    private TextView tvSecond;
    private TextView tvMinute;
    private TextView tvHour;
    private LinearLayout linearLapList;
    private FloatingActionButton fabAction, fabReset;
    private ExtendedFloatingActionButton fabLap;
    private Handler handler = new Handler();

    private boolean isRunning;
    private int hour;
    private int minute;
    private int sec; // 화면에 표시할 초
    private int millis; // 화면에 표시할 밀리초
    private int lap = 0;
    private long startTime; // 시작 버튼 누른 시점 (혹은 재시작 누른 시점)
    private long elapsedTime; // 최신 측정 시간 (시작 버튼 누른 이후 현재까지 경과 시간)
    private long totalElapsedTime; // 전체 측정 시간 (pause 누를때 elapsedTime 누적시킴)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        // 시작, 재시작 버튼 이벤트 처리
        fabAction.setOnClickListener((v) -> {
            isRunning = !isRunning;
            if(isRunning) {
                start();
            } else {
                pause();
            }
        });

        // 랩타임 기록 이벤트 처리
        fabLap.setOnClickListener((v) -> {
            if(isRunning){
                recordLapTime();
            } else {
                Toast.makeText(this, "Please, Start Stopwatch", Toast.LENGTH_SHORT).show();
            }
        });

        // 리셋 이벤트 처리
        fabReset.setOnClickListener((v) -> {
            reset();
        });
    }

    private void initView() {
        tvHour = findViewById(R.id.main_hour_textview);
        tvMinute = findViewById(R.id.main_minute_textview);
        tvSecond = findViewById(R.id.main_second_textview);
        tvMillis = findViewById(R.id.main_millisec_textview);
        linearLapList = findViewById(R.id.main_lap_list_linearlayout);
        fabAction = findViewById(R.id.main_action_floationbutton);
        fabLap = findViewById(R.id.main_laps_extended_floatingbutton);
        fabReset = findViewById(R.id.main_reset_floationbutton);
    }

    private void recordLapTime() {
        TextView textView = new TextView(this);
        lap++;
        textView.setText(String.format("%02d LAP : %02d:%02d:%02d.%02d", lap, hour, minute, sec, millis));
        textView.setTextSize(20);
        textView.setWidth(MATCH_PARENT);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        linearLapList.addView(textView, 0);
    }

    private void reset() {
        totalElapsedTime = 0L;
        lap = 0;
        isRunning = false;
        fabAction.setImageResource(R.drawable.ic_play_arrow_black_24dp);
        linearLapList.removeAllViews();

        handler.removeCallbacks(timeUpdater); // 대기중인 Runnable post 삭제
        handler.post(() -> {
            tvHour.setText("00:");
            tvHour.setTextColor(getResources().getColor(R.color.darker_gray));
            tvMinute.setText("00:");
            tvMinute.setTextColor(getResources().getColor(R.color.darker_gray));
            tvSecond.setText("00");
            tvMillis.setText(".00");
        });
    }

    private void pause() {
        // 버튼 모양을 재생으로 변경
        fabAction.setImageResource(R.drawable.ic_play_arrow_black_24dp);

        // 마지막 "elapsedTime"을 추가
        totalElapsedTime += elapsedTime;

        handler.removeCallbacks(timeUpdater);
    }

    private void start() {
        // 버튼 모양을 일시정지로 변경
        fabAction.setImageResource(R.drawable.ic_pause_black_24dp);
        // 부팅 이후 경과 시간 -> ms
        startTime = SystemClock.uptimeMillis();

        // UI 업데이트 하려고 함.
        handler.post(timeUpdater);
    }

    Runnable timeUpdater = new Runnable() {
        @Override
        public void run() {
            // Start 혹은 Restart 후 얼마나 경과했는지 측정
            elapsedTime = SystemClock.uptimeMillis() - startTime;
            // 마지막 누적 측정 시간에 최근 경과 시간을 더해서 화면에 표시한 시간을 계산
            long updateTime = totalElapsedTime + elapsedTime;
            // 1/100초 까지만 표시
            millis = (int)(updateTime % 1000) / 10;
            sec = (int)(updateTime / 1000) % 60;
            minute = (int)(updateTime / 1000) / 60;
            if(minute > 0) {
                tvMinute.setTextColor(Color.BLACK);
                tvMinute.setText(String.format("%02d:", minute));
            }
            hour = minute / 60;
            if(hour > 0) {
                tvMinute.setTextColor(Color.BLACK);
                tvMinute.setText(String.format("%02d:", hour));
            }
            tvSecond.setText(String.format("%02d", sec));
            tvMillis.setText(String.format(".%02d", millis));
            handler.post(this); // 다시 시간 측정과 UI 업데이트 요청. 무한루프개념. 여전히 다른 쓰레드도 동시 실행되니 문제 없음
        }
    };
}
