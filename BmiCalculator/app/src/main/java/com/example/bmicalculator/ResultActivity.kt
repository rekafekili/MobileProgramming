package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.jetbrains.anko.toast

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val height = intent.getStringExtra("height").toInt()
        val weight = intent.getStringExtra("weight").toInt()

//        Toast.makeText(this, "$height $weight", Toast.LENGTH_LONG).show()
        toast("$height $weight")
    }
}
