package com.swvl.movietask.ui.splash

import android.arch.lifecycle.MutableLiveData
import com.swvl.movietask.data.DataManager
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val dataManager: DataManager){

    fun seedMoviesToDB(){
        dataManager.seedMovies()
    }

    fun getInsertionFlag():MutableLiveData<Int>{
        return dataManager.getInsertionFlag()
    }
}