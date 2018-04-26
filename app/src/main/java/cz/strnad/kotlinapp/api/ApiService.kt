package cz.strnad.kotlinapp.api

import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET()
    fun getOrders(): Single<List<Order>>

}