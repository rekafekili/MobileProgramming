package com.example.mystopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text
import java.util.*
import kotlin.concurrent.timer

/**
 * 아래와 같이 스톱워치 앱을 만들 경우 <Windows>에서 제공하는 스톱워치랑 비교했을 때 성능이 많이 부족함
 *
 * 원인 : "Timertask"를 사용하고, UI 업데이트 소요 시간이 중첩되다보니 성능이 비교적 안좋음
 *
 * 개선 : "SystemClock"을 사용하여 좀더 정확한 스톱워치를 만들어야함. -> 자바코드로 구현
 */
class MainActivity : AppCompatActivity() {
    private var time = 0
    private var isRunning = false
    private var timerTask: Timer? = null
    private var lap = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_action_floationbutton.setOnClickListener {
            isRunning = !isRunning
            if (isRunning) {
                start()
            } else {
                pause()
            }
        }

        main_laps_floationbutton.setOnClickListener {
            if (isRunning) {
                recordLapTime()
            } else {
                Toast.makeText(this, "Please Play Stopwatch", Toast.LENGTH_SHORT).show()
            }
        }

        main_refresh_floationbutton.setOnClickListener {
            reset()
        }
    }

    private fun reset() {
        timerTask?.cancel()
        time = 0
        isRunning = false
        lap = 0
        main_action_floationbutton.setImageResource(R.drawable.ic_play_arrow_black_24dp)
        main_lap_list_linearlayout.removeAllViews()
//        main_second_textview.text = "0"
//        main_millisec_textview.text = ".00"

        /* TextView 에서 Post */
        main_second_textview.post {
            main_second_textview.text = "0"
            main_millisec_textview.text = ".00"
        }
    }

    private fun recordLapTime() {
        val lapTime = time
        val textView = TextView(this)
        textView.text = "${++lap} LAP : ${lapTime / 100}.${lapTime % 100}"
        textView.textSize = 30F
        textView.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        main_lap_list_linearlayout.addView(textView, 0) // 최상단 배치
    }

    private fun start() {
        // 버튼 이미지를 일시정지(pause)로 변경
        main_action_floationbutton.setImageResource(R.drawable.ic_pause_black_24dp)
        timerTask = timer(period = 10) {
            // 10 밀리세컨드마다 수행
            time++
            val second = time / 100
            val millis = time % 100

            runOnUiThread {
                main_second_textview.text = second.toString()
                main_millisec_textview.text = ".${millis}"
            }
        }
    }

    private fun pause() {
        // 버튼 이미지를 재생(play)으로 변경
        main_action_floationbutton.setImageResource(R.drawable.ic_play_arrow_black_24dp)
        timerTask?.cancel()
    }
}
