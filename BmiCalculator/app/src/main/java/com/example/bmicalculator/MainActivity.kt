package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        result_button.setOnClickListener {
            // 버튼을 클릭했을 때 실행될 동작
//            val intent = Intent(this, ResultActivity::class.java)
//            intent.putExtra("height", height_edittext.text.toString())
//            intent.putExtra("weight", weight_edittext.text.toString())
//            startActivity(intent)

            startActivity<ResultActivity>(
                "height" to height_edittext.text.toString(),
                "weight" to weight_edittext.text.toString())
        }
    }
}