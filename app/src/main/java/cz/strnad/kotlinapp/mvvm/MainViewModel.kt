package cz.strnad.kotlinapp.mvvm

import android.app.Application
import android.os.Bundle
import cz.strnad.kotlinapp.api.DefaultTranslator
import cz.strnad.kotlinapp.api.Error
import cz.strnad.kotlinapp.api.Order
import cz.strnad.kotlinapp.database.KotlinAppDatabase
import org.jetbrains.anko.defaultSharedPreferences

class MainViewModel(application: Application, bundle: Bundle) : ApiViewModel(application, bundle) {

    private val orderRepository: OrderRepository = OrderRepository(api, KotlinAppDatabase.getInstance(application))
    private val userRepository: UserRepository = UserRepository(api, KotlinAppDatabase.getInstance(application), application.defaultSharedPreferences)

    val orders = SingleLiveState<List<Order>, Error>();


    init {
        // load data from bundle
        orders.errorProcessor = CustomErrorProcessor(errorConverter, DefaultTranslator(application))
    }

    fun loadOrder() {
        orders.observe(orderRepository.getOrders(UserRepository.state.data.value))
    }

    fun loginUser(username: String, password: String) {
        userRepository.loginUser(username, password)
    }

}

