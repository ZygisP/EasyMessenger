package com.pinguxd.kotlinfirebasemessenger.register_login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.pinguxd.kotlinfirebasemessenger.R
import com.pinguxd.kotlinfirebasemessenger.messages.LatestMessagesActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        back_to_registration_textview.setOnClickListener {
            finish()
        }

        login_button_login.setOnClickListener {
            performLogin()
        }

    }

    private fun performLogin(){

        val email = email_edittext_login.text.toString()
        val password = password_edittext_login.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter text in email and password", Toast.LENGTH_SHORT).show()
            return
        }

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener

                // else if successful
                Log.d("Login", "Successfully logged in with an uid of: ${it.result?.user?.uid}")

                val intent = Intent(this, LatestMessagesActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener {
                Log.d("Login", "Failed to login: ${it.message}")
                Toast.makeText(this, "Please enter text in email and password", Toast.LENGTH_SHORT).show()
            }
    }
}