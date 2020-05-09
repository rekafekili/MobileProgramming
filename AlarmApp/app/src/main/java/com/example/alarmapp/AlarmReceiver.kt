package com.example.alarmapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val receivedState = intent.getStringExtra("state")

        // Broadcast 메시지를 수신하면 intent 와 함께 실행된다.
        // Case 1 : AlarmManager가 Broadcast
        // Case 2 : 직접 sendBroadcast() 해서 Broadcast
        Log.d("AlarmReceiver", "onReceive() Called")
        Log.d("AlarmReceiver", "Intent Extra : $receivedState")

        // state 값(ON, OFF)에 따라서 알림(Notification)을 보내고, 소리 재생 -> Service 이용
        // 1. Service Intent 생성, Service 실행
        val serviceIntent = Intent(context, SoundPlayService::class.java)
        serviceIntent.putExtra("state", receivedState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && receivedState == "ON") {
            context.startForegroundService(serviceIntent)
            Log.d("AlarmReceiver", "startForegroundService() Called")
            // 보안 정책으로 인해 Oreo 버전 이후부터는 무조건 Service는 Foreground로 실행되어야 함.
            // Service가 사용자에게 Notification을 통해 보이도록 만들어야함.
        } else {
            // 예전 버전이거나 Alarm State가 OFF일때는 Background에서 실행되도록 함(Ringtone Stop)
            context.startService(serviceIntent)
            Log.d("AlarmReceiver", "startService() Called")
        }
    }
}
