package com.example.alarmapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener {
    private var alarmManager: AlarmManager? = null
    private var pendingIntent: PendingIntent? = null
    private lateinit var receiverIntent: Intent // AlarmReceiver.class 시작을 위한 Intent
    private var selectedHour = 0
    private var selectedMinute = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        receiverIntent = Intent(applicationContext, AlarmReceiver::class.java)

        initListener()
    }

    private fun initListener() {
        main_alarm_on_button.setOnClickListener {
            // (1) 사용자가 TimePicker에 추가한 값을 불러오자.
            val hour = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                main_time_picker.hour
            } else {
                main_time_picker.currentHour
            }

            val minute = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                main_time_picker.minute
            } else {
                main_time_picker.currentMinute
            }

            setAlarm(hour, minute)
        }

        main_alarm_on_dialog_button.setOnClickListener {
            // (2) TimePicker Dialog를 띄워서 사용자가 Hour, Min을 선택할수 있도록
            val dialogFragment = TimePickerFragment(this)
            dialogFragment.show(supportFragmentManager, "TimePicker TAG")


        }

        main_alarm_off_button.setOnClickListener {
            /* ---- 알람 해제 ---- */
            // ON 버튼을 누르기 전에 OFF 버튼을 눌렀을 경우 오류를 방지
            alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            pendingIntent = PendingIntent.getBroadcast(
                applicationContext,
                0,
                receiverIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

            // 1. 알람 매니저에게 기존에 설정된 알람 취소 요청
            alarmManager!!.cancel(pendingIntent)
            Toast.makeText(applicationContext, "Alarm is Canceled.", Toast.LENGTH_SHORT).show()

            // 2. Broadcast Receiver 에게 새로운 부가데이터를 바로 전달
            receiverIntent.putExtra("state", "OFF")
            // 알람 매니저에게 요청할 필요 없이 지금 바로 Broadcast 메시지 송출
            sendBroadcast(receiverIntent)
        }
    }

    private fun setAlarm(hour: Int, minute: Int) {
        /* ---- 알람 설정 : 알람 매니저 활용 ---- */
        // 1. 알람 매니저 시스템 서비스 객체 선언
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        // 2. 알람리시버(BroadcastReceiver) 실행을 위한 intent 생성
        receiverIntent.putExtra("state", "ON")

        // 3. 지연 인텐트를 사용하여 알람 매니저에게 요청
        /**
         * requestCode : 값을 다르게 해서 여러 알람을 설정할 수 있다.
         * receiverIntent : 내가 실행하고 싶은 Intent
         * FLAG_UPDATE_CURRENT : 기존 pendingIntent가 있다면 부가 데이터(extra)만 업데이트함.
         */
        pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            0,
            receiverIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        // 4. 알람 설정 등록
        // 원하는 시각을 하려면 Calender 활용 가능
        val calendar = Calendar.getInstance() // 기존 Time Zone의 현재시각 얻기
        calendar.timeInMillis = System.currentTimeMillis() // 시스템의 Time Zone에 맞게 현재 시각으로 얻기
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        val alarmMillis = calendar.timeInMillis

        /**
         * 1. RTC_WAKEUP : Device가 꺼져있을 때 wakeup 해줌
         * 2. Long : 언제 깨워줄 것인지, 현재 시각 기준으로 언제쯤 깨울것인지?
         *   -> 원하는 시각을 하고 싶다면 Calender 사용
         *   -> 이전 시각으로 알람을 설정하면 바로 실행
         * 3. PendingIntent : 지연 인텐트
         */
        alarmManager?.set(
            AlarmManager.RTC_WAKEUP,
            alarmMillis,
            pendingIntent
        )

        Toast.makeText(applicationContext, "$hour:$minute 으로 설정되었습니다.", Toast.LENGTH_SHORT).show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        selectedHour = hourOfDay
        selectedMinute = minute

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            main_time_picker.hour = selectedHour
            main_time_picker.minute = selectedMinute
        } else {
            main_time_picker.currentHour = selectedHour
            main_time_picker.currentMinute = selectedMinute
        }

        setAlarm(selectedHour, selectedMinute)
    }
}
