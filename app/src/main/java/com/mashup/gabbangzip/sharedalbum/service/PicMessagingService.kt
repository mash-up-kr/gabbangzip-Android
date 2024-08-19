package com.mashup.gabbangzip.sharedalbum.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.mashup.gabbangzip.sharedalbum.R
import com.mashup.gabbangzip.sharedalbum.presentation.ui.splash.SplashActivity
import com.mashup.gabbangzip.sharedalbum.utils.NotificationUtil

class PicMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        with(remoteMessage) {
            notification?.let {
                sendNotification(it.title, it.body)
            }
        }
    }

    private fun sendNotification(title: String?, body: String?) {
        if (!title.isNullOrBlank() && !body.isNullOrBlank()) {
            val intent = Intent(this, SplashActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }

            val requestCode = 0
            val pendingIntent = PendingIntent.getActivity(
                this,
                requestCode,
                intent,
                PendingIntent.FLAG_IMMUTABLE,
            )

            val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
                .build()

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANEL_NAME,
                NotificationManager.IMPORTANCE_HIGH,
            )

            notificationManager.createNotificationChannel(channel)
            notificationManager.notify(NotificationUtil.getNotificationId(), notificationBuilder)
        } else {
            Log.d("PicMessagingService", "sendNotification: title or body is null")
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("PicMessagingService", "newToken: $token")
    }

    companion object {
        private const val CHANNEL_ID = "PIC"
        private const val CHANEL_NAME = "PIC 알람"
    }
}
