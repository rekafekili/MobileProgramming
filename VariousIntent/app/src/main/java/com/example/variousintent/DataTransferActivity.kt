package com.example.variousintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_data_transfer.*

class DataTransferActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_transfer)

        val receivedPerson = intent.getSerializableExtra("person") as Person

        transfer_received_data_1_textview.text = receivedPerson.name
        transfer_received_data_2_textview.text = receivedPerson.age.toString()
    }
}
