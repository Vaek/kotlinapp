package cz.strnad.kotlinapp.api

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("/login")
    fun login(@Body request: LoginRequest): Single<LoginResponse>

    @GET("/user")
    fun getUser(): Single<User>

    @GET("/orders")
    fun getOrders(@Query("userId") userId: String): Single<List<Order>>

}