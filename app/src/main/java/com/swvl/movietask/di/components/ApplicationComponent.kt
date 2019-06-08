package com.swvl.movietask.di.components

import android.content.Context
import com.google.gson.Gson
import com.swvl.movietask.MoviesTaskApplication
import com.swvl.movietask.data.local.RealmDBManager
import com.swvl.movietask.data.preferences.Preferences
import com.swvl.movietask.di.modules.ApplicationModule
import com.swvl.movietask.di.scope.ApplicationScope
import dagger.Component

@ApplicationScope
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: MoviesTaskApplication)
    fun getAppContext(): Context
    fun getAppPreference(): Preferences
    fun getGson(): Gson
    fun getRealmDBManager(): RealmDBManager
}