package com.example.cookingrecipe_appdync
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecipeAdapter(private val recipeList : ArrayList<foodrecipesusers>) : RecyclerView.Adapter<RecipeAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.recipeitem,
            parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = recipeList[position]

        holder.recipeName.text = currentitem.recipeName
        holder.recipeDescription.text = currentitem.recipeDescription
        holder.recipeIngredients.text = currentitem.recipeIngredients
        Glide.with(holder.itemView.context).load(currentitem.recipeimageurl).into(holder.recipeimageurl)
    }

    override fun getItemCount(): Int {

        return recipeList.size
    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val recipeName : TextView = itemView.findViewById(R.id.Nameofrecipe)
        val recipeDescription : TextView = itemView.findViewById(R.id.Descriptionofrecipe)
        val recipeIngredients : TextView = itemView.findViewById(R.id.Ingredientsofrecipe)
        val recipeimageurl : ImageView = itemView.findViewById(R.id.recipeImage)
    }

}