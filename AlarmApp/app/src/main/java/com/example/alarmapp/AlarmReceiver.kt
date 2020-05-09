package com.example.alarmapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // Broadcast 메시지를 수신하면 intent 와 함께 실행된다.
        // Case 1 : AlarmManager가 Broadcast
        // Case 2 : 직접 sendBroadcast() 해서 Broadcast
        Log.d("AlarmReceiver", "onReceive() Called")
        Log.d("AlarmReceiver", "Intent Extra : ${intent.getStringExtra("state")}")
    }
}
