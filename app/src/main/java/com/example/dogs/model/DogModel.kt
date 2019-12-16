package com.example.dogs.model

import com.google.gson.annotations.SerializedName

data class DogModel(
    @SerializedName("id")
    val breedId: String?,
    @SerializedName("name")
    val dogBreed: String?,
    @SerializedName("life_span")
    val lifeSpan: String?,
    @SerializedName("breed_group")
    val breedGroup: String?,
    @SerializedName("bred_for")
    val breadFor: String?,
    @SerializedName("temperament")
    val temperament: String?,
    @SerializedName("url")
    val imgUrl: String?
)