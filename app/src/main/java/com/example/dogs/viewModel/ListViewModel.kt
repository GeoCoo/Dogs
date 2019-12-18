package com.example.dogs.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dogs.dao.DogDb
import com.example.dogs.model.DogModel
import com.example.dogs.service.ApiService
import com.example.dogs.service.RetrofitInstance
import com.example.dogs.utils.SharedPrefHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class ListViewModel(application: Application): BaseViewModel(application) {

    private val dogService = RetrofitInstance()
    private val disposable = CompositeDisposable()
    private var prefHelper = SharedPrefHelper(getApplication())

    val dogs = MutableLiveData<List<DogModel>>()
    val dogLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        fetchFromRemote()
    }

    private fun fetchFromRemote(){
        loading.value = true
        disposable.add(
            dogService.getDogs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object:DisposableSingleObserver<List<DogModel>>(){
                    override fun onSuccess(t: List<DogModel>) {
                        storeDogsLocally(t)

                    }

                    override fun onError(e: Throwable) {
                        dogLoadError.value = true
                        loading.value = false
                        e.printStackTrace()
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    private fun dogRetrieved(dogList:List<DogModel>){
        dogs.value = dogList
        dogLoadError.value=false
        loading.value = false;
    }

    private fun storeDogsLocally(dogList:List<DogModel>){
        launch {
            DogDb(getApplication()).dogDao().deleteAll()
            val result = DogDb(getApplication()).dogDao().insertAll(
                *dogList.toTypedArray()
            )

            var i =0;
            while (i<dogList.size){
                dogList[i].uuid = result[i].toInt()
                ++i
            }
            dogRetrieved(dogList)
        }
        prefHelper.saveUpdateTime(System.nanoTime())
    }
}