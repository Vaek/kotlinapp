package cz.strnad.kotlinapp.database

import android.app.Application

/**
 * Created by vaclavstrnad on 24/04/2018.
 */

fun Application.getDatabase(): KotlinAppDatabase {
    return KotlinAppDatabase.getInstance(this)
}