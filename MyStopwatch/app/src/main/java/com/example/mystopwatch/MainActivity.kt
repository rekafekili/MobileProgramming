package com.example.mystopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private var time = 0
    private var isRunning = false
    private var timerTask: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_action_floationbutton.setOnClickListener {
            isRunning = !isRunning
            if(isRunning) {
                start()
            } else {
                pause()
            }
        }
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
                main_millisec_textview.text = millis.toString()
            }
        }
    }

    private fun pause() {
        // 버튼 이미지를 재생(play)으로 변경
        main_action_floationbutton.setImageResource(R.drawable.ic_play_arrow_black_24dp)
        timerTask?.cancel()
    }
}
