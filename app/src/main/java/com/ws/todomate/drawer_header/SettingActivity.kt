package com.ws.todomate.drawer_header

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ws.todomate.Anonymous
import com.ws.todomate.R
import com.ws.todomate.databinding.ActivitySettingBinding
import com.ws.todomate.login.JoinActivity
import com.ws.todomate.login.loginActivity

class SettingActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding : ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        auth = Firebase.auth
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting)

        //뒤로가기
        binding.backBtn.setOnClickListener {
            finish()
        }

        //가입
        binding.joinBtn.setOnClickListener {
            if(Anonymous.anays == true) {
                val intent = Intent(this, AfterJoinActivity::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this, EmailActivity::class.java)
                startActivity(intent)
            }
        }

        //로그아웃
        binding.logoutBtn.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, loginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

    }
}