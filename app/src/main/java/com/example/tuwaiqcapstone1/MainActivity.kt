package com.example.tuwaiqcapstone1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tuwaiqcapstone1.Repository.TaskRepository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TaskRepository.init(this)
    }
}