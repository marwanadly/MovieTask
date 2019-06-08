package com.swvl.movietask.data.local

import android.arch.lifecycle.MutableLiveData
import com.swvl.movietask.model.MovieEntry
import com.swvl.movietask.util.Constants
import io.realm.Realm
import io.realm.RealmResults

class RealmDBManager(var realm: Realm) {

     val insertionFlag = MutableLiveData<Int>()

    fun retrieveAllMovies():RealmResults<MovieEntry>{
        return realm.where(MovieEntry::class.java).findAll()
    }

    fun saveMovies(movies: List<MovieEntry>) {
        realm.executeTransactionAsync({realm ->realm.insertOrUpdate(movies)},{ insertionFlag.value = Constants.INSERTED_SUCCESSFULLY },{ error->insertionFlag.value = Constants.INSERT_FAILED})
    }
}