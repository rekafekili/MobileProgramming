package com.example.bmicalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_result.*
import org.jetbrains.anko.toast

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Get Data from MainActivity
        val height = intent.getStringExtra("height").toInt()
        val weight = intent.getStringExtra("weight").toInt()

        // Calculate BMI
        val bmi = weight / Math.pow((height/100.0), 2.0)

        // Show Result with TextView
        when {
            bmi >= 35 -> result_textview.text = "고도 비만"
            bmi >= 30 -> result_textview.text = "2단계 비만"
            bmi >= 25 -> result_textview.text = "1단계 비만"
            bmi >= 23 -> result_textview.text = "과체중"
            bmi >= 18.5 -> result_textview.text = "정상"

            else -> result_textview.text = "저체중"
        }

        // Show Image
        when {
            bmi >= 23 -> result_imageview.setImageResource(R.drawable.ic_sentiment_dissatisfied_black_24dp)
            bmi >= 18.5 -> result_imageview.setImageResource(R.drawable.ic_sentiment_satisfied_black_24dp)
            else -> result_imageview.setImageResource(R.drawable.ic_sentiment_neutral_black_24dp)
        }

        toast("키: $height, 몸무게: $weight, BMI: $bmi")
    }
}
