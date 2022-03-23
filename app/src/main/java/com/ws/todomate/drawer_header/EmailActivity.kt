package com.ws.todomate.drawer_header

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.ws.todomate.Anonymous
import com.ws.todomate.R

class EmailActivity : AppCompatActivity() {

    private var TAG = EmailActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email)

        val emailArea = findViewById<TextView>(R.id.emailArea)
        emailArea.setText(Anonymous.getEmail())
    }
}