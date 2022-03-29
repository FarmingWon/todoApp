package com.ws.todomate

import android.util.Log
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Anonymous {

    companion object{

        var anays : Boolean = false // 익명 체크

        fun getEmail() : String{ // 이메일받기
            val user = Firebase.auth.currentUser
            var email : String = ""
            user?.let {
                email = user.email.toString()
            }
            return email
        }

        fun getUid() : String{
            var auth: FirebaseAuth = Firebase.auth
            return auth.currentUser?.uid.toString()
        }
    }
}