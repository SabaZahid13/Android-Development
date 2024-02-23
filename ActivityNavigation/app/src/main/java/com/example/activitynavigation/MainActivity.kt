package com.example.activitynavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var changeActivityButton: Button
    lateinit var messageEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        messageEditText = findViewById(R.id.etMessage)
        changeActivityButton = findViewById(R.id.newActivityBtn)
        changeActivityButton.setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivity2::class.java)
            val message = messageEditText.text.toString()
            intent.putExtra("message_key", message)
            startActivity(intent)
        }
    }
}