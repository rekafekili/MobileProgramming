package com.example.bmicalculator

import android.os.Bundle
import androidx.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Check Saved Data
        loadData()

        result_button.setOnClickListener {
            // Save Data
            saveData(height_edittext.text.toString().toInt(), weight_edittext.text.toString().toInt())

            // Go To ResultActivity
            startActivity<ResultActivity>(
                "height" to height_edittext.text.toString(),
                "weight" to weight_edittext.text.toString())
        }
    }

    private fun loadData() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val height = pref.getInt("KEY_HEIGHT", 0)
        val weight = pref.getInt("KEY_WEIGHT", 0)

        if(height != 0 && weight != 0) {
            height_edittext.setText(height.toString())
            weight_edittext.setText(weight.toString())
        }
    }

    private fun saveData(height: Int, weight: Int) {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()
        editor.putInt("KEY_HEIGHT", height)
            .putInt("KEY_WEIGHT", weight)
            .apply()
    }
}