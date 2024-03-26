package com.srbastian.sendsms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    lateinit var editTextMessage : EditText
    lateinit var editPhone : EditText
    lateinit var send : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etMessage = findViewById<EditText>(R.id.etSMS)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val sendButton = findViewById<Button>(R.id.btnSend)

        sendButton.setOnClickListener {

        }
    }
}