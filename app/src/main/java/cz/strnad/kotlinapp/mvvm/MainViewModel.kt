package cz.strnad.kotlinapp.mvvm

import android.app.Application
import android.os.Bundle
import cz.strnad.kotlinapp.api.DefaultTranslator
import cz.strnad.kotlinapp.database.KotlinAppDatabase

class MainViewModel(application: Application, bundle: Bundle) : ApiViewModel(application, bundle) {

    val orders: OrderRepository = OrderRepository(api, errorConverter, KotlinAppDatabase.getInstance(application))

    init {
        // load data from bundle
        orders.translator = DefaultTranslator(application)
    }

    fun loadOrder() {
        orders.getOrders()
    }

}