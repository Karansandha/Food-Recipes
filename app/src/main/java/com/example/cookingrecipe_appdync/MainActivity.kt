package com.example.cookingrecipe_appdync

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cookingrecipe_appdync.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        lateinit var auth: FirebaseAuth
    }

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         auth = FirebaseAuth.getInstance()
        binding = ActivityMainBinding.inflate(layoutInflater)
         setContentView(binding.root)
         supportActionBar?.title = "Main Activity"

         binding.signout.setOnClickListener {
             auth.signOut()

         }
     }
         override fun onResume()
         {
             super.onResume()
             binding.userdetails.text = updateData()
        }


   private  fun updateData(): String{
            return "Email : ${auth.currentUser?.email}"
        }


    }

