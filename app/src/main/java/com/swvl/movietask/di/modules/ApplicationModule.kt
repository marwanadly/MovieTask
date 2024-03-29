package com.swvl.movietask.di.modules

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.swvl.movietask.data.preferences.AppPreferences
import com.swvl.movietask.data.preferences.Preferences
import com.swvl.movietask.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module(includes = [DataModule::class,NetworkModule::class])
class ApplicationModule(context: Context) {

    private var mContext: Context = context

    @Provides
    @ApplicationScope
    fun provideContext(): Context {
        return mContext
    }

    @Provides
    @ApplicationScope
    fun provideAppPreference(context: Context): Preferences {
        return AppPreferences(context)
    }

    @Provides
    @ApplicationScope
    fun provideGson() : Gson {
        return GsonBuilder().create()
    }
}