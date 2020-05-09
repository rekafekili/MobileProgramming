package com.example.alarmapp

import android.app.Service
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.IBinder

class SoundPlayService : Service() {
    companion object {
        var ringtone : Ringtone? = null
    }
    // 한 번 만들어지면 계속 실행됨
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    // 초기에 실행될 때 한번만 호출
    override fun onCreate() {
        ringtone = RingtoneManager.getRingtone(applicationContext, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM))

    }

    // 실행될 때마다 호출
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val receivedState = intent?.getStringExtra("state")
        startForeground(1, )

        if(receivedState == "ON") {
            ringtone?.play()
        } else {
            ringtone?.stop()
        }

//        return super.onStartCommand(intent, flags, startId) // Default : START_STICKY
        return START_STICKY
    }

    override fun onDestroy() {
        if(ringtone != null) {
            ringtone?.stop()
        }
        super.onDestroy()
    }
}
