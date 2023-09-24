package com.example.c23g.activities

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.c23g.R
import com.example.c23g.activities.broadcast.AirplaneModeBroadcastReceiver

class MainActivity : AppCompatActivity() {

    lateinit var receiver: AirplaneModeBroadcastReceiver
    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        receiver = AirplaneModeBroadcastReceiver()
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(AirplaneModeBroadcastReceiver(), it)
        } }
    override fun onStop() { super.onStop()
        unregisterReceiver(receiver)
    } }
