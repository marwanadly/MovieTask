package com.swvl.movietask.data.local

import android.arch.lifecycle.MutableLiveData
import com.swvl.movietask.model.MovieEntry
import com.swvl.movietask.util.Constants
import io.realm.Case
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort

class RealmDBManager(var realm: Realm) {

     val insertionFlag = MutableLiveData<Int>()

    fun retrieveAllMovies():RealmResults<MovieEntry>{
        return realm.where(MovieEntry::class.java).findAll()
    }

    fun saveMovies(movies: List<MovieEntry>) {
        realm.executeTransactionAsync({realm ->realm.insertOrUpdate(movies)},{ insertionFlag.value = Constants.INSERTED_SUCCESSFULLY },{ error->insertionFlag.value = Constants.INSERT_FAILED})
    }

    fun searchForMovies(criteria:String) :List<MovieEntry>{
        val searchResult = realm.where(MovieEntry::class.java)
            .contains("title",criteria,Case.INSENSITIVE)
            .sort("year",Sort.ASCENDING,"rating",Sort.DESCENDING)
            .findAll().groupBy { groupBy-> groupBy.year }.values.map { groupedMovies-> groupedMovies.take(5) }
        val finalResult = ArrayList<MovieEntry>()
        searchResult.map { sortedMovies-> finalResult.addAll(sortedMovies) }
        return finalResult.toList()
    }
}