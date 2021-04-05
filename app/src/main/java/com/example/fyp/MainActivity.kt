package com.example.fyp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<ImageButton>(R.id.start)
        val sp = Audio(this,"en","GB")
        sp!!.generate_audio("Welcome to BlindVision.Tap on the screen to start.")


        button.setOnClickListener {
            sp!!.generate_audio("Choose your requirement")
            val intent = Intent(this, ChoiceActivity::class.java)
            startActivity(intent)
        }
    }
}