package com.example.cookingrecipe_appdync

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.cookingrecipe_appdync.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Login"

        binding.goregister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()

        }


        binding.login.setOnClickListener {
            val email_login = binding.emaillogin.text.toString()
            val password_login = binding.passlogin.text.toString()
            if (email_login.isNotEmpty() && password_login.isNotEmpty()) {
                RegisterActivity.auth.signInWithEmailAndPassword(email_login, password_login)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                            val text = "Login Successfully"
                            val duration = Toast.LENGTH_SHORT

                            val toast = Toast.makeText(this, text, duration)
                            toast.show()

                        }

                    }.addOnFailureListener {
                        Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
                    }
            }

        }
    }
}
