package com.example.c23g.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.c23g.R
import com.example.c23g.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}