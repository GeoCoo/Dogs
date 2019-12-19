package com.example.dogs.viewModel

import android.app.Application
import android.os.ParcelUuid
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dogs.dao.DogDb
import com.example.dogs.model.DogModel
import kotlinx.coroutines.launch


class DetailViewModel(application: Application) : BaseViewModel(application) {

    val dogLiveData = MutableLiveData<DogModel>()

    fun fetch(uuid: Int){
//        val dogPug = DogModel("1","pug","15","breedGroup","","","")
//        dogLiveData.value = dogPug
        launch {
            val dog = DogDb(getApplication()).dogDao().getDog(uuid)
            dogLiveData.value = dog

        }

    }


}