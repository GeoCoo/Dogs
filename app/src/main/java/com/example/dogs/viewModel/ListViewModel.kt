package com.example.dogs.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dogs.model.DogModel

class ListViewModel: ViewModel() {

    val dogs = MutableLiveData<List<DogModel>>()
    val dogLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){

    }

    private fun fetch(){
        
    }
}