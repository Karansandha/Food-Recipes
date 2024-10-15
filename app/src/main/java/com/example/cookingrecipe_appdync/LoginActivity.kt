package com.example.cookingrecipe_appdync

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cookingrecipe_appdync.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding:  ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


         binding = ActivityLoginBinding.inflate(layoutInflater)


        setContentView(binding.root)
        supportActionBar?.title = "Login Activity"

        binding.goregister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

        binding.forgotpassword.setOnClickListener{
            startActivity(Intent(this,ForgotActivity::class.java))
            finish()
        }



        binding.loginBtn.setOnClickListener {
            val email_login = binding.emaillogin.text.toString()
            val password_login = binding.passlogin.text.toString()
            if (email_login.isNotEmpty() && password_login.isNotEmpty()) {
                RegisterActivity.auth.signInWithEmailAndPassword(email_login, password_login)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                            val text = "Welcome"
                            val duration = Toast.LENGTH_SHORT
                            val toast = Toast.makeText(this, text, duration)
                            toast.show()

                        }
                        else
                        {
                            Toast.makeText(this, "Incorrect Password", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                    }
            else{
                Toast.makeText(this, "Empty fields are not allowed", Toast.LENGTH_LONG).show()
            }
            }

        }




}
