package com.example.cookingrecipe_appdync

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cookingrecipe_appdync.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    companion object {
        lateinit var auth: FirebaseAuth
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Register Activity"

        binding.login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding.submit.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            val confrmpass = binding.confirmpassword.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty() && confrmpass.isNotEmpty()) {
                if (password == confrmpass) {
                    RegisterActivity.auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                            if (it.isSuccessful) {
                                startActivity(Intent(this, LoginActivity::class.java))
                                finish()
                                val text = "Register Successfully"
                                val duration = Toast.LENGTH_LONG

                                val toast = Toast.makeText(this, text, duration)
                                toast.show()


                            }
                            else {
                                Toast.makeText(this, "Email is already registered.", Toast.LENGTH_LONG)
                                    .show()
                            }
                        }
                } else {
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_LONG).show()
                }
            }
        else
        {
            Toast.makeText(this, "Empty fields are not allowed  ", Toast.LENGTH_LONG).show()
        }

            }
            }
        }






