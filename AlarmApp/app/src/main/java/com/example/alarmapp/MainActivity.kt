package com.example.alarmapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var alarmManager: AlarmManager? = null
    private var pendingIntent: PendingIntent? =  null
    private lateinit var receiverIntent: Intent // AlarmReceiver.class 시작을 위한 Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        receiverIntent = Intent(applicationContext, AlarmReceiver::class.java)

        initListener()
    }

    private fun initListener() {
        main_alarm_on_button.setOnClickListener {
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

            // 4. 알람 설정 등록 (10초 뒤)
            /**
             * 1. RTC_WAKEUP : Device가 꺼져있을 때 wakeup 해줌
             * 2. Long : 언제 깨워줄 것인지, 현재 시각 기준으로 언제쯤 깨울것인지? 
             *   -> 원하는 시각을 하고 싶다면 Calender 사용
             *   -> 이전 시각으로 알람을 설정하면 바로 실행
             * 3. PendingIntent : 지연 인텐트
             */
            alarmManager?.set(
                AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + 10000,
                pendingIntent
            )

            Toast.makeText(applicationContext, "10초 뒤로 알람을 설정했습니다.", Toast.LENGTH_SHORT).show()
        }

        main_alarm_off_button.setOnClickListener {
            /* ---- 알람 해제 ---- */
            // ON 버튼을 누르기 전에 OFF 버튼을 눌렀을 경우 오류를 방지
            if(alarmManager != null && pendingIntent != null) {
                // 1. 알람 매니저에게 기존에 설정된 알람 취소 요청
                alarmManager!!.cancel(pendingIntent)
            }

            // 2. Broadcast Receiver 에게 새로운 부가데이터를 바로 전달
            receiverIntent.putExtra("state", "OFF")
            // 알람 매니저에게 요청할 필요 없이 지금 바로 Broadcast 메시지 송출
            sendBroadcast(receiverIntent)

            // 종료되고 몇 초 후, 보안 정책 강화로 인해 RemoteServiceException 이 발생함
            //  -> onStartCommand() 내에 startForeground() 메소드를 호출해야함
            //  -> Notification 필요!
        }
    }
}
