package com.example.gastrotech

import com.google.firebase.messaging.FirebaseMessagingService
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {

    companion object {
        private const val TAG = "FCMService"
        private const val CHANNEL_ID = "gastrotech_notifications"
        private const val CHANNEL_NAME = "gastrotech Notifications"
        private const val CHANNEL_DESCRIPTION = "Notificaciones de eventos y reservas"
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Log.d(TAG, "From: ${remoteMessage.from}")

        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload: ${remoteMessage.data}")
        }

        remoteMessage.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
            sendNotification(it.title ?: "GastroTech", it.body ?: "Tienes una nueva notificación")
        } ?: run {
            if (remoteMessage.data.isNotEmpty()) {
                val title = remoteMessage.data["title"] ?: "GatroTech"
                val body = remoteMessage.data["body"] ?: "Tienes una nueva notificación"
                sendNotification(title, body)
            }
        }
    }


    private fun sendNotification(title: String, messageBody: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_ONE_SHOT
        )

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = CHANNEL_DESCRIPTION
                enableLights(true)
                enableVibration(true)
            }
            notificationManager.createNotificationChannel(channel)
        }
        val notificationId = System.currentTimeMillis().toInt()
        notificationManager.notify(notificationId, notificationBuilder.build())
    }
}


