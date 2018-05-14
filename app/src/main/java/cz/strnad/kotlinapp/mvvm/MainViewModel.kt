package cz.strnad.kotlinapp.mvvm

import android.app.Application
import android.os.Bundle
import cz.strnad.kotlinapp.api.DefaultTranslator
import cz.strnad.kotlinapp.api.Order
import cz.strnad.kotlinapp.database.KotlinAppDatabase

class MainViewModel(application: Application, bundle: Bundle) : ApiViewModel(application, bundle) {

    val orders: OrderRepository = OrderRepository(api, errorConverter, KotlinAppDatabase.getInstance(application))
    val users: UserRepository = UserRepository(api, errorConverter, KotlinAppDatabase.getInstance(application))

    val ordersCache = RepositoryCache<List<Order>, Error>()

    init {
        // load data from bundle
        orders.translator = DefaultTranslator(application)
    }

    fun loadOrder() {
        orders.getOrders(users.data.value)
    }

    fun loginUser(username: String, password: String) {
        users.logInUser(username, password)
    }

}