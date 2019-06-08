package com.swvl.movietask.ui.main

import android.arch.lifecycle.MutableLiveData
import com.swvl.movietask.data.DataManager
import com.swvl.movietask.model.MovieEntry
import javax.inject.Inject

class MainViewModel @Inject constructor(private val dataManager: DataManager) {

    var moviesList = MutableLiveData<List<MovieEntry>>()

    fun retrieveAllMovies(){
        moviesList.value = dataManager.retrieveAllMovies()
    }

    fun searchForMovies(criteria: String): List<MovieEntry> {
        return dataManager.searchForMovies(criteria)
    }
}