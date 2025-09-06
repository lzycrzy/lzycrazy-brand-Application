package com.example.lzycrazy.withlogin.notifications

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lzycrazy.R
import java.text.SimpleDateFormat
import java.util.*

class NotificationActivity : AppCompatActivity() {

    private lateinit var notificationContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

        notificationContainer = findViewById(R.id.notificationContainer)

        // Simulate adding a new notification every 5 seconds
        val handler = Handler(Looper.getMainLooper())
        val addNotificationRunnable = object : Runnable {
            override fun run() {
                addNotification("New message at ${getCurrentTime()}")
                handler.postDelayed(this, 5000)
            }
        }
        handler.post(addNotificationRunnable)
    }

    private fun addNotification(title: String) {
        val inflater = LayoutInflater.from(this)
        val notificationView = inflater.inflate(R.layout.notification_view, null)

        val titleView = notificationView.findViewById<TextView>(R.id.notificationTitle)
        val timeView = notificationView.findViewById<TextView>(R.id.notificationTime)

        titleView.text = title
        timeView.text = getRelativeTime(System.currentTimeMillis())

        // Add new notification at the top
        notificationContainer.addView(notificationView, 0)
    }

    private fun getCurrentTime(): String {
        val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        return sdf.format(Date())
    }

    private fun getRelativeTime(timestamp: Long): String {
        val now = System.currentTimeMillis()
        val diff = now - timestamp
        return when {
            diff < 60_000 -> "${diff / 1000} sec ago"
            diff < 3_600_000 -> "${diff / 60_000} min ago"
            else -> SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(timestamp))
        }
    }
}
