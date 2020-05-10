package com.example.alarmapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class SoundPlayService : Service() {
    companion object {
        var ringtone: Ringtone? = null
    }

    // 한 번 만들어지면 계속 실행됨
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    // 초기에 실행될 때 한번만 호출
    override fun onCreate() {
        ringtone = RingtoneManager.getRingtone(
            applicationContext,
            RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        )

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel("Default", "Channel Nmae", NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.description = "Description about Channel"

            notificationManager.createNotificationChannel(notificationChannel)
        }

    }

    // 실행될 때마다 호출
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val receivedState = intent?.getStringExtra("state")
        Log.d("SoundPlayService", "onStartCommand() Started : Extra State = $receivedState")

        if (receivedState == "ON") {
            ringtone?.play()
            showNotification()
        } else {
            ringtone?.stop()
        }

//        return super.onStartCommand(intent, flags, startId) // Default : START_STICKY
        return START_STICKY
    }

    private fun showNotification() {
        // 1. Notification 클릭시 새로운 Intent 로 다른 화면을 띄울 준비
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        // 2. Notification Builder를 이용해서 Notification 객체 생성
        val builder = NotificationCompat.Builder(this, "Default")
        builder.setAutoCancel(true)
            .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
            .setContentTitle("Notification Start")
            .setContentText("Notification Alarm is Played")
            .setContentIntent(pendingIntent) // 사용자가 Noti를 확인하고 클릭하면 지연인텐트를 실행
        val notification = builder.build()

        // 3. Notification Manager 준비, Channel 생성
//        val notificationManager =
//            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val notificationChannel =
//                NotificationChannel("Default", "Channel Nmae", NotificationManager.IMPORTANCE_HIGH)
//            notificationChannel.description = "Description about Channel"
//            // 채널을 만들어주기만 하면 OK
//            notificationManager.createNotificationChannel(notificationChannel)
//
//            // 중요도를 LOW로 바꾼 후에 다시 HIGH로 수정하면 HIGH가 적용되지 않음
//            //  -> createNotificationChannel()의 설명을 보면 중요도 수정은 "더 낮은 경우에만" 적용이 됨
//        }

        // 4. Notification Show
//        notificationManager.notify(1, notification) // 이 시점에 Notification이 상단바에 표시됨

        // startForeground()로 알림 표시까지 진행
        startForeground(2, notification) // 현재 서비스를 Foreground로 가져오고 동시에 Notification 표시

        // Notification을 지울 수 있도록 하기 위해서는 Background로 접근해야함.
        stopForeground(false)
        // Service를 다시 Foreground를 취소하고 Notification은 남겨둠
        // Notification을 사용자가 클릭하면 autoCancel(true) 되도록 설정되어 있음

        Log.d("SoundPlayService", "showNotification() Finished")
    }

    override fun onDestroy() {
        if (ringtone != null) {
            ringtone?.stop()
        }
        super.onDestroy()
    }
}
