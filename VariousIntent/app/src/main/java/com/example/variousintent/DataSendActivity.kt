package com.example.variousintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_data_send.*

// TODO : 1. Primitive
// TODO : 2. Serializable
// TODO : 3. Parcelable
// TODO : 4. Bundle
// TODO : 5. static
class DataSendActivity : AppCompatActivity() {
    companion object {
        var senderName = ""
        const val senderActivity = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_send)
//        Toast.makeText(this, "onCreate()", Toast.LENGTH_SHORT).show()

        val subject = Course("Mobile Programming", 3, 4.0)
        val serializablePerson = SerializablePerson("SeongYun", 26, "dodri0605@naver.com")
        val sendIntent = Intent(applicationContext, DataReceiveActivity::class.java)

        send_button.setOnClickListener {
            // 1. Primitive Type
            sendIntent.putExtra("name", subject.name) // String
            sendIntent.putExtra("point", subject.point) // Int
            sendIntent.putExtra("grade", subject.grade) // Double

            // 2. Serializable
            sendIntent.putExtra("serializable", serializablePerson)

            // 3. Parcelable
            val parcelableSubject = ParcelableCourse("모바일 프로그래밍", 4, 4.5)
            sendIntent.putExtra("parcelable", parcelableSubject)

            // 4. Bundle
            val bundle = Bundle()
            bundle.putString("bundle_name", subject.name)
            bundle.putInt("bundle_point", subject.point)
            bundle.putDouble("bundle_grade", subject.grade)
            sendIntent.putExtras(bundle)

            // 5. static
            senderName = "Cho Seong Yun"

            startActivity(sendIntent)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Toast.makeText(this, "onNewIntent()", Toast.LENGTH_SHORT).show()
    }
}
