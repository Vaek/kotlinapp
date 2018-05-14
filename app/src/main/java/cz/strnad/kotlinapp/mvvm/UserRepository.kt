package cz.strnad.kotlinapp.mvvm

import cz.strnad.kotlinapp.api.ApiService
import cz.strnad.kotlinapp.api.Error
import cz.strnad.kotlinapp.api.User
import cz.strnad.kotlinapp.database.KotlinAppDatabase
import okhttp3.ResponseBody
import retrofit2.Converter

/**
 * Created by vaclavstrnad on 24/04/2018.
 */
class UserRepository(api: ApiService,
                     errorConverter: Converter<ResponseBody, Error>,
                     database: KotlinAppDatabase) : OfflineApiRepository<User>(api, errorConverter, database) {

    internal fun logInUser(username: String, password: String) {
        // TODO log in user correctly
        data.value = User("", "email@email.com", "User 1")
    }
}
