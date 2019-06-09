package com.swvl.movietask.ui.details

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import com.swvl.movietask.data.remote.ApiConstants
import com.swvl.movietask.network.MovieDbService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(private val movieDbService: MovieDbService){

    val imageURL = MutableLiveData<String>()

    @SuppressLint("CheckResult")
    fun getMovieImage(movieTitle:String){
        movieDbService.getMovieImageData(ApiConstants.API_KEY,movieTitle)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({apiResponse->
                    if(apiResponse.results.isNotEmpty()){
                        val url = "https://image.tmdb.org/t/p/w500/${apiResponse.results[0].imagePath}"
                        imageURL.value = url
                    }
            },{error->Timber.e(error)})
    }
}