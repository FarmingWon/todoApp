package com.ws.todomate.drawer_header

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ws.todomate.Anonymous
import com.ws.todomate.MainActivity
import com.ws.todomate.R
import com.ws.todomate.databinding.ActivityAfterJoinBinding

class AfterJoinActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding : ActivityAfterJoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after_join)

        auth = Firebase.auth
        binding = DataBindingUtil.setContentView(this, R.layout.activity_after_join)

        binding.joinBtn.setOnClickListener {
                val email = binding.emailArea.text.toString()
                val password = binding.passwordArea.text.toString()
                val credential = EmailAuthProvider.getCredential(email, password)

                auth.currentUser!!.linkWithCredential(credential)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Anonymous.anays = false
                            Toast.makeText(this, "성공", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            //
                        }
                    }
        }

    }
}