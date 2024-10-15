package com.example.cookingrecipe_appdync

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.cookingrecipe_appdync.databinding.ActivityAddrecipeBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.UUID

class AddRecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddrecipeBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    private lateinit var uri: Uri
    private lateinit var dialog: Dialog
    lateinit var addrecipe: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityAddrecipeBinding.inflate(layoutInflater)

        setContentView(binding.root)
        supportActionBar?.title = "Add Recipe Activity"

        binding.backtoactivity.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))

        }

        addrecipe = findViewById(R.id.addrecipe)

        val galleryImage = registerForActivityResult(ActivityResultContracts.GetContent()) {
            addrecipe.setImageURI(it)
            if (it != null) {
                uri = it

            }

        }


        addrecipe.setOnClickListener {
            galleryImage.launch("image/*")
        }


        val uid = RegisterActivity.auth.currentUser?.uid
        databaseReference = FirebaseDatabase.getInstance().getReference("foodrecipesusers")
        binding.sharerecipe.setOnClickListener {

            loadingbar()


            val recipeName = binding.recipeName.text.toString()
            val recipeDescription = binding.recipeDescription.text.toString()
            val recipeIngredients = binding.recipeingredients.text.toString()

            val userUuid = UUID.randomUUID()
            val useruuid = userUuid.toString()
            val imageUuid = UUID.randomUUID()
            val imguuid = imageUuid.toString()

            if (recipeName.isNotEmpty() && recipeDescription.isNotEmpty() && recipeIngredients.isNotEmpty()) {


                    storageReference =
                        FirebaseStorage.getInstance().getReference("foodrecipesusersimg/")
                            .child("image/" + imguuid)
                    storageReference.putFile(uri).addOnSuccessListener { task ->
                        task.metadata!!.reference!!.downloadUrl.addOnSuccessListener { url ->
                            loadingbarhide()
                            Toast.makeText(
                                this,
                                "Recipe image uploaded successfully",
                                Toast.LENGTH_LONG
                            ).show()
                            val imgUrl = url.toString()


                            val recipe = foodrecipesusers(
                                useruuid,
                                recipeName,
                                recipeDescription,
                                recipeIngredients,
                                imgUrl
                            )



                            if (uid != null) {
                                databaseReference.child("Usersdata").push().setValue(recipe)
                                    .addOnCompleteListener {
                                        if (it.isSuccessful) {
                                            loadingbarhide()
                                            Toast.makeText(
                                                this,
                                                "Recipe Uploaded successfully",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            startActivity(Intent(this, MainActivity::class.java))

                                        } else {
                                            loadingbarhide()
                                            Toast.makeText(
                                                this,
                                                "Failed to upload recipe",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }


                            }
                        }


                    }.addOnFailureListener {
                        loadingbarhide()
                        Toast.makeText(this, "Failed to upload the image", Toast.LENGTH_SHORT)
                            .show()
                    }

                }

            else
            {
                loadingbarhide()
                Toast.makeText(this, "Empty fields are not allowed", Toast.LENGTH_LONG).show()
            }



        }

    }

    private fun loadingbar()
    {
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.loading)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

    private fun loadingbarhide()
    {
        dialog.dismiss()
    }


}
