package com.example.dogs.service

import com.example.dogs.model.DogModel
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("DevTides/DogsApi/master/dogs.json")
    fun getDogs(): Single<List<DogModel>>
}