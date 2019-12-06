package com.example.dogs.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dogs.model.DogModel

class ListViewModel: ViewModel() {

    val dogs = MutableLiveData<List<DogModel>>()
    val dogLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        val dogPug = DogModel("1","pug","15","breedGroup","","","")
        val dogCorgi = DogModel("1","corgi","15","breedGroup","","","")
        val dogHuskey = DogModel("1","huskey","15","breedGroup","","","")
        val dogList = arrayListOf(dogCorgi,dogHuskey,dogPug)

        dogs.value = dogList
        dogLoadError.value = false
        loading.value = false
    }
}