package com.swvl.movietask.di.modules

import android.content.Context
import com.google.gson.Gson
import com.swvl.movietask.data.DataManager
import com.swvl.movietask.data.local.RealmDBManager
import dagger.Module
import io.realm.RealmConfiguration
import javax.inject.Singleton
import dagger.Provides
import io.realm.Realm


@Module
class DataModule {


    @Provides
    fun providesRealmDBManager(realm: Realm): RealmDBManager {
        return RealmDBManager(realm)
    }

    @Provides
    fun providesRealm(realmConfiguration: RealmConfiguration): Realm {
        return Realm.getInstance(realmConfiguration)
    }

    @Provides
    @Singleton
    fun providesRealmConfiguration(): RealmConfiguration {
        val builder = RealmConfiguration.Builder()
        builder.deleteRealmIfMigrationNeeded()
        return builder.build()
    }

    @Provides
    @Singleton
    fun providesDataManager(context: Context, gson: Gson, realmDBManager: RealmDBManager):DataManager {
        return DataManager(context,gson,realmDBManager)
    }
}