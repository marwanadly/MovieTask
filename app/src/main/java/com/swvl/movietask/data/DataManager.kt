package com.swvl.movietask.data

import android.annotation.SuppressLint
import android.content.Context
import com.google.gson.Gson
import com.google.gson.internal.`$Gson$Types`
import com.swvl.movietask.data.local.RealmDBManager
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

    fun getMoviesFromAssets():Observable<List<MovieEntry>>{
        return Observable.fromCallable {
            val type = `$Gson$Types`.newParameterizedTypeWithOwner(null, List::class.java, MovieEntry::class.java)
            return@fromCallable mGson.fromJson<List<MovieEntry>>(CommonUtils.loadJSONFromAsset(mContext, Constants.MOVIES_FILE_NAME), type)
        }
    }

    fun saveMoviesToDB(movies:List<MovieEntry>){
        realmDBManager.saveMovies(movies)
    }

    @SuppressLint("CheckResult")
    fun seedMovies(){
        getMoviesFromAssets().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({movies-> saveMoviesToDB(movies)},{ err-> Timber.e(err)})
    }
}