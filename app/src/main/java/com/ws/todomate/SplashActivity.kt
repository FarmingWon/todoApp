package com.ws.todomate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ws.todomate.databinding.ActivitySplashBinding
import com.ws.todomate.login.loginActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySplashBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        auth = Firebase.auth

        if(auth.currentUser?.uid == null){
            Handler().postDelayed({
                startActivity(Intent(this, loginActivity::class.java))
                finish()
            },2000)
        }else{
            Handler().postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            },2000)
        }

    }
}