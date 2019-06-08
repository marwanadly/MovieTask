package com.swvl.movietask.data

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.swvl.movietask.data.local.RealmDBManager
import com.swvl.movietask.model.MovieArray
import com.swvl.movietask.model.MovieEntry
import com.swvl.movietask.util.CommonUtils
import com.swvl.movietask.util.Constants
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject


class DataManager @Inject constructor(private val mContext:Context,
                                      private val mGson:Gson,
                                      private val realmDBManager: RealmDBManager) {

    private fun getMoviesFromAssets():Observable<MovieArray>{
        return Observable.fromCallable {
            val type = object : TypeToken<MovieArray>() {}.type
            return@fromCallable mGson.fromJson<MovieArray>(CommonUtils.loadJSONFromAsset(mContext, Constants.MOVIES_FILE_NAME), type)
        }
    }

    private fun saveMoviesToDB(movies:List<MovieEntry>){
        realmDBManager.saveMovies(movies)
    }

    @SuppressLint("CheckResult")
    fun seedMovies(){
        getMoviesFromAssets().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({fileResult-> saveMoviesToDB(fileResult.movies);},{ err-> Timber.e(err)})
    }

    fun retrieveAllMovies():List<MovieEntry> = realmDBManager.retrieveAllMovies().toList()
    fun getInsertionFlag(): MutableLiveData<Int> = realmDBManager.insertionFlag
}