package com.example.dogs.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class DogModel(
    @ColumnInfo(name = "breed_id" )
    @SerializedName("id")
    val breedId: String?,

    @ColumnInfo(name = "dog_name" )
    @SerializedName("name")
    val dogBreed: String?,

    @ColumnInfo(name = "life_span" )
    @SerializedName("life_span")
    val lifeSpan: String?,

    @ColumnInfo(name = "breed_group" )
    @SerializedName("breed_group")
    val breedGroup: String?,

    @ColumnInfo(name = "bred_for" )
    @SerializedName("bred_for")
    val breadFor: String?,

    @ColumnInfo(name = "temperament" )
    @SerializedName("temperament")
    val temperament: String?,

    @ColumnInfo(name = "img_url" )
    @SerializedName("url")
    val imgUrl: String?
)