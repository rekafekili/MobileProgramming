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
    companion object {
        const val SUCCESS = 101
    }

    private var count: Int = 0
//    private val uiHandler: UIHandler = UIHandler()

    // (1) UI 쓰레드에서 시간이 걸리는 작업을 하면 안된다. -> Thread 필요
    // (2) Thread 에서는 UI Thread 의 View를 조작할 수 없음
    // (3) 쓰레드간 데이터 전달 방법이 필요 -> runOnUiThread, Handler...
    // (Usage 1) Handler 클래스 확장 사용
    //   1. Handler.obtainMessage() : Bundle 객체 전달
    //   2. Handler.obtainMessage(what, obj) : 사용자 정의 객체 전달
    // (Usage 2) Handler 원형 클래스 객체 사용
//    private val handler = UIHandler()
    // (Usage 3) Handler 객체 없이 UI 업데이트

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
            for (i in 0 until 5) {
                sleep(1000)
//                main_button_up.text = (count).toString() // -> CalledFromWrongThreadException 발생

                /**
                 * (Usage 1-1) UI 업데이트를 하고 싶을 때 Handler 를 이용해서 Message 를 보내자.
                 */
//                val message = uiHandler.obtainMessage()
//                val bundle = Bundle()
//                bundle.putInt("count", ++count)
//                message.data = bundle
//                uiHandler.sendMessage

                /**
                 * (Usage 1-2) obtainsMessage(what, obj); int, object
                 */
//                count++
//                val message = uiHandler.obtainMessage(SUCCESS, Person(age = 20+count))
//                // Bundle 데이터를 담을 수 있지만, what, obj 만 사용하는 예제로
//                message.sendToTarget()

                /**
                 * (Usage 2) Handler 객체 이용
                 */
//                handler.post{
//                    main_textview.text = (++count).toString()
//                }

                /**
                 * (Usage 3) Handler 객체 없이 UI 업데이트
                 */
                runOnUiThread{
                    main_textview.text = (++count).toString()
                }

                Log.d("[     Thread Test     ]", "count = $count")
            }
        }
    }

    inner class UIHandler : Handler() { // UI Thread 에 접근해서 조작할 수 있도록 하는 클래스
        override fun handleMessage(msg: Message) {
            // 다른 Thread 에서 Message Queue 를 통해 받아온 메시지를 처리

            // (1-1) 처리할 데이터가 하나만 있을 경우 Bundle을 사용한다.
//            val receivedBundle = msg.data
//            val value = receivedBundle.getInt("count")
//            main_textview.text = (value).toString()

            // (1-2) 여러 종류의 데이터를 처리하고 싶은 경우 what을 사용하여 분기한다.
            when(msg.what) {
                SUCCESS -> {
                    val person = msg.obj as Person
                    main_textview.text = "${person.name}'s Age = ${person.age}"
                }
            }
        }
    }

    data class Person (val age: Int = 20, val name: String = "홍길동")
}
