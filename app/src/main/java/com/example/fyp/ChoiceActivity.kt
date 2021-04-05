package com.example.fyp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class ChoiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice)
        val audio = Audio(this,"en","GB");
        val button1 = findViewById<Button>(R.id.Detection);
        val button2 = findViewById<Button>(R.id.DnA);

        button1.setOnClickListener {
            audio!!.generate_audio("Detection has started.")
            val intent = Intent(this, DetectionActivity::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            audio!!.generate_audio("Distance and angle easurement has started.")
            val intent = Intent(this, DnA_Activity::class.java)
            startActivity(intent)
        }
    }
}