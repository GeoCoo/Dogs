package com.example.dogs.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.dogs.model.DogModel

@Dao
interface DogDao {

    @Insert
    suspend fun insertAll(vararg dogs : DogModel) : List<Long>

    @Query("SELECT * FROM dogmodel")
    suspend fun getAllDogs():List<DogModel>

    @Query("SELECT * FROM dogmodel WHERE uuid = :dogId ")
    suspend fun getDog(dogId: Int): DogModel

    @Query("DELETE FROM dogmodel")
    suspend fun deleteAll()

}