package cz.strnad.kotlinapp.mvvm

import android.content.SharedPreferences
import cz.strnad.kotlinapp.api.ApiService
import cz.strnad.kotlinapp.database.KotlinAppDatabase
import cz.strnad.mvvm.Repository

open class ApiRepository(protected val api: ApiService) : Repository() {

}

open class DatabaseRepository(protected val database: KotlinAppDatabase) : Repository() {

}

open class OfflineApiRepository(api: ApiService,
                                protected val database: KotlinAppDatabase) : ApiRepository(api) {

}

open class UniversalRepository(api: ApiService,
                               database: KotlinAppDatabase,
                               protected val preferences: SharedPreferences) : OfflineApiRepository(api, database) {

}