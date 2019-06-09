package com.swvl.movietask.util

import android.content.Context

class Constants {

    companion object {
        const val PREFERENCES_NAME = "moviesTask"
        const val PREFERENCES_MODE = Context.MODE_PRIVATE
        const val FIRST_LAUNCH_PREFERENCE = "first_launch"
        const val FIRST_LAUNCH_PREFERENCE_VALUE = 1
        const val MOVIES_FILE_NAME  = "movies.json"
        const val INSERTED_SUCCESSFULLY = 0
        const val INSERT_FAILED = 1
        const val DETAILS_EXTRA = "movie_details"
    }
}