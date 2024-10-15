package com.example.cookingrecipe_appdync

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cookingrecipe_appdync.databinding.ActivityForgotpasswordBinding

class ForgotActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityForgotpasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Forgot Password"

        binding.gobacktologinscreen.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding.resetpassword.setOnClickListener {
            val password_reset = binding.emailforgotpass.text.toString()
            if (password_reset.isNotEmpty()) {
                RegisterActivity.auth.sendPasswordResetEmail(password_reset)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()

                            Toast.makeText(this, "Password reset email has been sent", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
            }
            else
            {
                Toast.makeText(this, "Password field is empty", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}
