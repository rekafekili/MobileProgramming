package com.example.alarmapp

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import java.util.*

class TimePickerFragment(
    private val onTimeSetListener: TimePickerDialog.OnTimeSetListener
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        return TimePickerDialog(
            activity,
            onTimeSetListener,
            hour,
            minute,
            DateFormat.is24HourFormat(activity)
        )
    }

//    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
//        // 사용자가 설정을 완료하면 호출
//        Toast.makeText(activity, "Setting Time : $hourOfDay:$minute", Toast.LENGTH_SHORT).show()
//    }
}