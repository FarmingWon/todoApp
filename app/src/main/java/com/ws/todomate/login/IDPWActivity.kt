package com.ws.todomate.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ws.todomate.Anonymous
import com.ws.todomate.MainActivity
import com.ws.todomate.R
import com.ws.todomate.databinding.ActivityIdpwactivityBinding

class IDPWActivity : AppCompatActivity() {

    private lateinit var binding : ActivityIdpwactivityBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_idpwactivity)

        auth = Firebase.auth
        binding = DataBindingUtil.setContentView(this, R.layout.activity_idpwactivity)

        binding.loginBtn.setOnClickListener {
            val email = binding.idArea.text.toString()
            val password = binding.pwdArea.text.toString()
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        Anonymous.anays = false
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}