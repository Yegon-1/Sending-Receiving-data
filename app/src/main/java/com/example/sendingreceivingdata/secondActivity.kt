package com.example.sendingreceivingdata

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class secondActivity : AppCompatActivity() {

    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val receivedMessageView: TextView = findViewById(R.id.receivedMessage)
        editText = findViewById(R.id.editTextSecond)
        val sendButton: Button = findViewById(R.id.sendButtonSecond)

        // Receiving data from MainActivity
        intent?.data?.let { data: Uri ->
            val message = data.schemeSpecificPart // Extract the message
            receivedMessageView.text = "Received: $message"
        }

        // Sending data back to MainActivity
        sendButton.setOnClickListener {
            val replyMessage = editText.text.toString()
            val replyIntent = Intent(this, MainActivity::class.java).apply {
                putExtra("reply", replyMessage) // Sending reply using intent extras
            }
            startActivity(replyIntent)
        }
    }
}