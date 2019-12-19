package com.example.dogs.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.dogs.R
import com.example.dogs.model.DogModel
import com.example.dogs.utils.getProgressDrawable
import com.example.dogs.utils.loadImage
import com.example.dogs.view.ListFragmentDirections
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
        holder.view.imageView.loadImage(dogList[position].imgUrl, getProgressDrawable(holder.view.imageView.context))
        holder.view.setOnClickListener{
            val action = ListFragmentDirections.actionDetailFragment()
            action.dogUuid = dogList[position].uuid
            Navigation.findNavController(it).navigate(ListFragmentDirections.actionDetailFragment())
        }
    }

    class DogViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}