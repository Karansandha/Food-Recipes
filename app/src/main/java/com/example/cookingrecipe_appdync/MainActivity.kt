package com.example.cookingrecipe_appdync

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cookingrecipe_appdync.RegisterActivity.Companion.auth
import com.example.cookingrecipe_appdync.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseReference : DatabaseReference
    private lateinit var  recipeRecyclerview : RecyclerView
    private lateinit var recipeList : ArrayList<foodrecipesusers>


     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)



         binding = ActivityMainBinding.inflate(layoutInflater)
         setContentView(binding.root)
         supportActionBar?.title = "Main Activity"


         recipeRecyclerview = findViewById(R.id.recipeListfetch)
         recipeRecyclerview.layoutManager = LinearLayoutManager(this)
         recipeRecyclerview.setHasFixedSize(true)
         recipeList = arrayListOf<foodrecipesusers>()

         databaseReference = FirebaseDatabase.getInstance().getReference("foodrecipesusers").child("Usersdata")
         databaseReference.addValueEventListener(object: ValueEventListener {

             override fun onDataChange(snapshot: DataSnapshot) {

                 if(snapshot.exists())
                 {
                     for(recipeSnapshot in snapshot.children){

                         val userdata = recipeSnapshot.getValue(foodrecipesusers::class.java)
                         recipeList.add(userdata!!)

                     }

                     recipeRecyclerview.adapter = RecipeAdapter(recipeList)


                 }
             }

             override fun onCancelled(error: DatabaseError) {
                 TODO("Not yet implemented")
             }

         })


         binding.addrecipe.setOnClickListener {
             startActivity(Intent(this,AddRecipeActivity::class.java))
         }

          binding.logout.setOnClickListener {
            auth.signOut()
             startActivity(Intent(this, LoginActivity::class.java))
             finish()
         }
}

        override fun onResume()
         {
             super.onResume()
             binding.logginuser.text = updateData()
         }
     }

   private  fun updateData(): String{
            return " ${auth.currentUser?.email}"
        }





