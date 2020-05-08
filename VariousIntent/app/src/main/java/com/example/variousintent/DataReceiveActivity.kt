package com.example.variousintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_data_receive.*

class DataReceiveActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_receive)

        val receiveIntent = intent

        // 1. Primitive Type
        receive_subject_name.text = receiveIntent.getStringExtra("name")
        receive_subject_point.text = receiveIntent.getIntExtra("point", 1).toString()
        receive_subject_grade.text = receiveIntent.getDoubleExtra("grade", 2.0).toString()

        // 2. Serializable
        val person = receiveIntent.getSerializableExtra("serializable") as SerializablePerson
        receive_person_name.text = person.name
        receive_person_age.text = person.age.toString()
        receive_person_nickname.text = person.nickname

        // 3. Parcelable
        val parcelableSubject = receiveIntent.getParcelableExtra<ParcelableCourse>("parcelable")
        receive_parcelable_subject_name.text = parcelableSubject.name
        receive_parcelable_subject_point.text = parcelableSubject.point.toString()
        receive_parcelable_subject_grade.text = parcelableSubject.grade.toString()

        // 4. Bundle
        val receivedBundle = receiveIntent.extras
        receive_bundle_subject_name.text = receivedBundle!!.getString("bundle_name")
        receive_bundle_subject_point.text = receivedBundle.getInt("bundle_point").toString()
        receive_bundle_subject_grade.text = receivedBundle.getDouble("bundle_grade").toString()

        // 5. Static
        receive_static_sender_name.text = DataSendActivity.senderName
        receive_static_sender_activity.text = DataSendActivity.senderActivity
    }
}
