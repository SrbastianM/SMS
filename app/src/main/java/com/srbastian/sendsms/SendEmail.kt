package com.srbastian.sendsms

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.EditText
import com.srbastian.sendsms.databinding.ActivitySendEmailBinding

class SendEmail : AppCompatActivity() {
    lateinit var emailBinding: ActivitySendEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        emailBinding = ActivitySendEmailBinding.inflate(layoutInflater)
        val view = emailBinding.root
        setContentView(view)

        emailBinding.btnSendEmail.setOnClickListener {
            val userAdress = emailBinding.etAddress.text.toString()
            val userSubject = emailBinding.etSubject.text.toString()
            val userMail = emailBinding.etMail.text.toString()
            sendEmail(userAdress, userSubject, userMail)
        }
    }

    private fun sendEmail(userAdress: String, userSubject: String, userMail: String) {
        val emailAdresses = arrayOf(userAdress)
        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse("mailto:")
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emailAdresses)
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, userSubject)
        emailIntent.putExtra(Intent.EXTRA_TEXT, userMail)

        if(emailIntent.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(emailIntent, "Choose an App"))

        }

    }

}