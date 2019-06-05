package com.swvl.movietask

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.swvl.movietask.di.components.ApplicationComponent
import com.swvl.movietask.di.components.DaggerApplicationComponent
import com.swvl.movietask.di.modules.ApplicationModule
import timber.log.Timber

class MoviesTaskApplication : Application(){

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        if(com.swvl.movietask.BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)

        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
        component.inject(this)
    }
}