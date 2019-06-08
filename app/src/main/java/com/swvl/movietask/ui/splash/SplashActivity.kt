package com.swvl.movietask.ui.splash

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.swvl.movietask.MoviesTaskApplication
import com.swvl.movietask.R
import com.swvl.movietask.data.preferences.Preferences
import com.swvl.movietask.di.components.ApplicationComponent
import com.swvl.movietask.di.components.DaggerMainComponent
import com.swvl.movietask.ui.main.MainActivity
import com.swvl.movietask.util.Constants
import kotlinx.android.synthetic.main.activity_splash.*
import timber.log.Timber
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    private fun getApplicationComponent(): ApplicationComponent = (application as MoviesTaskApplication).component

    @Inject lateinit var splashViewModel: SplashViewModel
    @Inject lateinit var appPreferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        DaggerMainComponent.builder().applicationComponent(getApplicationComponent())
            .build().inject(this)

        if(appPreferences.readInt(Constants.FIRST_LAUNCH_PREFERENCE) == Constants.FIRST_LAUNCH_PREFERENCE_VALUE){

            startActivity(Intent(this,MainActivity::class.java));this.finish()

        }else{
            splashViewModel.seedMoviesToDB()
            splashViewModel.getInsertionFlag().observeForever {
                    value-> when(value){
                Constants.INSERTED_SUCCESSFULLY -> {
                    startActivity(Intent(this,MainActivity::class.java))
                    this.finish()
                    appPreferences.writeInt(Constants.FIRST_LAUNCH_PREFERENCE,Constants.FIRST_LAUNCH_PREFERENCE_VALUE)
                }
                Constants.INSERT_FAILED -> Timber.e("Failed to insert to realm")
             }
            }
        }
    }
}
