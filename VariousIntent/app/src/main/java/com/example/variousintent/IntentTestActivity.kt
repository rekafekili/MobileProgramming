package com.example.variousintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_intent_test.*

class IntentTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_test)
        Toast.makeText(this, "onCreate()", Toast.LENGTH_SHORT).show()

        test_button.setOnClickListener {
            val intent = Intent(this, IntentTestActivity::class.java)
//            intent.flags = (Intent.FLAG_ACTIVITY_SINGLE_TOP) or (Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Toast.makeText(this, "onNewIntent()", Toast.LENGTH_SHORT).show()
    }
}
