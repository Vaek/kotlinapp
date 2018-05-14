package cz.strnad.kotlinapp.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET()
    fun getOrders(@Query("userId") userId: String): Single<List<Order>>

}