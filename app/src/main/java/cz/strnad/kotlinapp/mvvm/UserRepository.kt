package cz.strnad.kotlinapp.mvvm

import android.content.SharedPreferences
import cz.strnad.kotlinapp.api.ApiService
import cz.strnad.kotlinapp.api.LoginRequest
import cz.strnad.kotlinapp.api.LoginResponse
import cz.strnad.kotlinapp.api.User
import cz.strnad.mvvm.Repository
import io.reactivex.Single
import io.reactivex.functions.Consumer

/**
 * Created by vaclavstrnad on 24/04/2018.
 */
class UserRepository(val api: ApiService,
                     val preferences: SharedPreferences) : Repository() {

    companion object {
        val state = SingleLiveState<User, Error>()
    }

    internal fun getLoggedUser() {
        state.observe(Single.just(User("", "email@email.com", "User 1"))
                .doOnSuccess {
                    preferences.edit()
                            .apply { /* TODO store user data */ }
                            .apply()
                })
    }

    internal fun loginUser(username: String, password: String): Single<LoginResponse> {
        // TODO log in user correctly
        return api.login(LoginRequest(username, password)).doOnSuccess(Consumer { getLoggedUser() })
    }
}
