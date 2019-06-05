package com.swvl.movietask.data.local

import com.swvl.movietask.model.MovieEntry
import io.realm.Realm
import io.realm.RealmResults

class RealmDBManager(var realm: Realm) {

    fun retrieveAllMovies():RealmResults<MovieEntry>{
        return realm.where(MovieEntry::class.java).findAllAsync()
    }

    fun saveMovies(movies: List<MovieEntry>) {
        realm.executeTransactionAsync { realm -> realm.copyToRealmOrUpdate(movies) }
    }

    fun getMoviesCount(): Long {
        return realm.where(MovieEntry::class.java).count()
    }
}