package com.example.sendingreceivingdata

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editTextMain)
        val sendButton: Button = findViewById(R.id.main_send_button)
        val repliedMessageView: TextView = findViewById(R.id.repliedMessage)

        // Sending data to SecondActivity
        sendButton.setOnClickListener {
            val message = editText.text.toString()
            val intent = Intent(this, SecondActivity::class.java).apply {
                data = Uri.parse("message:$message")
            }
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Receiving reply from SecondActivity
        intent?.let {
            if (it.hasExtra("reply")) {
                val reply = it.getStringExtra("reply")
                repliedMessageView.text = "Reply: $reply"
            }
        }
    }
}