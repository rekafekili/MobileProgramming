package com.example.quickbuttongame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_click.*

class ClickActivity : AppCompatActivity() {
    private var remainTime = 60000
    private var enemyCount = 30
    private var startTime: Long? = null
    private var elapsedTime: Long? = null
    private var handler = Handler()
    private var isGaming = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_click)

        isGaming = true

        startTime = SystemClock.uptimeMillis()
        handler.post(TimeCounter())
        EnemyThread().start()

        click_button.setOnClickListener {
            click_point_textview.text = (--enemyCount).toString()
            if (enemyCount == 0) {
                isGaming = false
                click_point_textview.text = "You Win!!"
                click_button.isClickable = false
            }
        }
    }

    inner class EnemyThread : Thread() {
        override fun run() {
            while (isGaming) {
                sleep(200)
                handler.post {
                    if (isGaming)
                        click_point_textview.text = (++enemyCount).toString()
                }
            }
        }
    }

    inner class TimeCounter : Runnable {
        override fun run() {
            if (isGaming) {
                elapsedTime = SystemClock.uptimeMillis() - startTime!!
                val time = remainTime - elapsedTime!!.toInt()

                val millis = (time % 1000) / 10
                val sec = (time / 1000)

                click_second_textview.text = "$sec"
                click_millis_textview.text = ".${millis}"

                if (time > 0) {
                    handler.post(this)
                } else {
                    handler.removeCallbacks(this)
                    isGaming = false
                    if(enemyCount != 0) {
                        runOnUiThread {
                            click_point_textview.text = "You Lose..."
                            click_button.isClickable = false
                        }
                    }
                }
            }
        }
    }
}