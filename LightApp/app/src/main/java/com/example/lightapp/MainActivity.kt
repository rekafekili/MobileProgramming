package com.example.lightapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 한국기술교육대 2020년 1학기 모바일 프로그래밍 조성윤
 *
 * LightApp
 * 여백을 없애기
 * 앱 이름(LightApp)이 나오지 않도록
 * 스위치를 없애고, 화면이 터치하면 ON, OFF 번갈아가며 바뀌도록
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var isLight = false
        framelayout.setOnClickListener {
            if(isLight) {
                light_on_imageview.visibility = View.INVISIBLE
                light_off_imageview.visibility = View.VISIBLE
                isLight = false
            } else {
                light_on_imageview.visibility = View.VISIBLE
                light_off_imageview.visibility = View.INVISIBLE
                isLight = true
            }
        }
    }
}
