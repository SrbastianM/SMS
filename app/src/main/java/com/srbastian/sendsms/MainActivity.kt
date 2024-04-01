package com.srbastian.sendsms

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    lateinit var editTextMessage: EditText
    lateinit var editPhone: EditText
    lateinit var send: Button
    lateinit var email: Button

    var userMessage: String = ""
    var userPhone: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etMessage = findViewById<EditText>(R.id.etSMS)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val sendButton = findViewById<Button>(R.id.btnSend)
        val changeEmail = findViewById<Button>(R.id.btnEmail)

        sendButton.setOnClickListener {
            getDataFromUser(etMessage, etPhone)
        }
        changeEmail.setOnClickListener {
            changeView()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            val smsManager: SmsManager
            if (Build.VERSION.SDK_INT >= 23) {
                smsManager = this.getSystemService(SmsManager::class.java)
            } else {
                smsManager = SmsManager.getDefault()
            }
            smsManager.sendTextMessage(userPhone, null, userMessage, null, null)
            Toast.makeText(applicationContext, "Message Send", Toast.LENGTH_LONG).show()
        }
    }
    private fun getDataFromUser(message: EditText, phone: EditText) {
        userMessage = message.text.toString()
        userPhone = phone.text.toString()

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.SEND_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.SEND_SMS),
                1
            )
        } else {
            val smsManager: SmsManager
            if (Build.VERSION.SDK_INT >= 23) {
                smsManager = this.getSystemService(SmsManager::class.java)
            } else {
                smsManager = SmsManager.getDefault()
            }
            smsManager.sendTextMessage(userPhone, null, userMessage, null, null)
            Toast.makeText(applicationContext, "Message Send", Toast.LENGTH_LONG).show()
        }
    }
    private fun changeView() {
        val intent = Intent(this@MainActivity, SendEmail::class.java)
        startActivity(intent)
        
    }
}