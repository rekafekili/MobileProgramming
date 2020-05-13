package com.example.mythread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

// Note : Ctrl + Alt + F - 핃드 생성
class MainActivity : AppCompatActivity() {
    private var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_button_up.setOnClickListener {
            Thread.sleep(1000)
            count += 5
            main_textview.text = (count).toString()
        }

        main_button_down.setOnClickListener {
            main_textview.text = (--count).toString()
        }
    }
}
