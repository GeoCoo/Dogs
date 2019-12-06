package com.example.dogs.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dogs.R
import com.example.dogs.model.DogModel
import kotlinx.android.synthetic.main.list_item_dog.view.*

class DogListAdapter(val dogList: ArrayList<DogModel>) :RecyclerView.Adapter<DogListAdapter.DogViewHolder>() {


    fun updateDogList(newDogList: List<DogModel>){
        dogList.clear()
        dogList.addAll(newDogList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_dog, parent, false)
        return DogViewHolder(view)
    }

    override fun getItemCount(): Int = dogList.size

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.view.dogName.text = dogList[position].dogBreed
        holder.view.lifeSpan.text = dogList[position].lifeSpan
    }

    class DogViewHolder(var view: View) : RecyclerView.ViewHolder(view) {}
}