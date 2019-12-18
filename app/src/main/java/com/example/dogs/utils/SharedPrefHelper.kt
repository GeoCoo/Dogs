package com.example.dogs.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit

class SharedPrefHelper {


    companion object{

        private const val PREF_TIME = "Pref_time"


    private var prefs : SharedPreferences ?= null

        @Volatile private var instance :SharedPrefHelper ?= null

        private val LOCK = Any()

        operator  fun invoke(context: Context):SharedPrefHelper = instance ?: synchronized(LOCK){
            instance ?: buildHelper(context).also{
                instance= it
            }
        }

        private fun buildHelper(context: Context) : SharedPrefHelper{
            prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return SharedPrefHelper()
        }


        }

    fun saveUpdateTime(time:Long){
        prefs?.edit(commit = true){
            putLong(PREF_TIME, time)
        }
    }
}