package com.ws.todomate.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ws.todomate.MainActivity
import com.ws.todomate.R
import com.ws.todomate.databinding.ActivityJoinBinding

class JoinActivity : AppCompatActivity() {

    private lateinit var binding : ActivityJoinBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_join)
        auth = Firebase.auth

        binding.joinBtn.setOnClickListener {
            var isGotoJoin = true
            val idArea = binding.idArea.text.toString()
            val pwd1 = binding.pwdArea1.text.toString()
            val pwd2 = binding.pwdArea2.text.toString()

            if(idArea.isEmpty()){
                Toast.makeText(this, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()
                isGotoJoin = false
            }
            if(pwd1.isEmpty()){
                Toast.makeText(this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
                isGotoJoin = false
            }
            if(pwd2.isEmpty()){
                Toast.makeText(this, "비밀번호체크를 입력해주세요", Toast.LENGTH_SHORT).show()
                isGotoJoin = false
            }
            if(pwd1.length<6){
                Toast.makeText(this, "비밀번호 6자리 이상으로 입력해주세요", Toast.LENGTH_SHORT).show()
                isGotoJoin = false
            }
            if(!pwd1.equals(pwd2)){
                Toast.makeText(this, "비밀번호를 같게 해주세요", Toast.LENGTH_SHORT).show()
                isGotoJoin = false
            }

            if(isGotoJoin){
                val email = binding.idArea.text.toString()
                val password1 = binding.pwdArea1.text.toString()
                auth.createUserWithEmailAndPassword(email, password1)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(this,"성공", Toast.LENGTH_LONG).show()
                            val intent = Intent(this, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(this,"실패", Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }
    }
}