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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(serviceIntent)
        } else {
            context.startService(serviceIntent)
        }
    }
}
