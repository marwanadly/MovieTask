package com.swvl.movietask.di.components

import com.swvl.movietask.di.scope.ActivityScope
import com.swvl.movietask.ui.details.MovieDetailsActivity
import com.swvl.movietask.ui.main.MainActivity
import com.swvl.movietask.ui.splash.SplashActivity
import dagger.Component

@Component(dependencies = [ApplicationComponent::class])
@ActivityScope
interface MainComponent {
    fun inject(splashActivity: SplashActivity)
    fun inject(mainActivity: MainActivity)
    fun inject(movieDetailsActivity: MovieDetailsActivity)
}