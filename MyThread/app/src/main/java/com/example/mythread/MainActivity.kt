package com.example.mythread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

// Note : Ctrl + Alt + F - 핃드 생성
class MainActivity : AppCompatActivity() {
    private var count: Int = 0
    private val uiHandler: UIHandler = UIHandler()

    // (1) UI 쓰레드에서 시간이 걸리는 작업을 하면 안된다. -> Thread 필요
    // (2) Thread 에서는 UI Thread 의 View를 조작할 수 없음
    // (3) 쓰레드간 데이터 전달 방법이 필요 -> runOnUiThread, Handler...
    // (3-1) Handler 클래스 확장 사용
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_button_up.setOnClickListener {
            val thread = MyThread()
            thread.start()
        }

        main_button_down.setOnClickListener {
            main_textview.text = (--count).toString()
        }
    }

    inner class MyThread : Thread() {
        override fun run() {
            for(i in 0 until 5) {
                sleep(1000)
                Log.d("[     Thread Test     ]", "count = ${++count}")
//                main_button_up.text = (count).toString() // -> CalledFromWrongThreadException 발생

//                runOnUiThread { // UI Thread에서 실행하도록 해준다.
//                    main_textview.text = (count).toString() // -> CalledFromWrongThreadException 발생
//                }

                // UI 업데이트를 하고 싶을 때 Handler 를 이용해서 Message 를 보내자.
            }
        }
    }

    inner class UIHandler : Handler() { // UI Thread 에 접근해서 조작할 수 있도록 하는 클래스
        override fun handleMessage(msg: Message) {
            //
        }
    }
}
