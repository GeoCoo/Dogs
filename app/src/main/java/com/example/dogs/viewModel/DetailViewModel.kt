package com.example.dogs.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dogs.model.DogModel


class DetailViewModel : ViewModel() {

    val dogLiveData = MutableLiveData<List<DogModel>>()

    fun fetch(){
        val dogPug = DogModel("1","pug","15","breedGroup","","","")
    }


}