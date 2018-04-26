package cz.strnad.kotlinapp.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by vaclavstrnad on 25/04/2018.
 */
class ApiBuilder(apiUrl: String,
                 logLevel: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.NONE,
                 modifyHttpClient: (OkHttpClient.Builder) -> Any = {}) {

    private val retrofit: Retrofit

    init {
        val httpClientBuilder = OkHttpClient.Builder()

        if (logLevel != HttpLoggingInterceptor.Level.NONE) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = logLevel
            httpClientBuilder.addInterceptor(loggingInterceptor)
        }

        modifyHttpClient(httpClientBuilder)

        retrofit = Retrofit.Builder()
                .baseUrl(apiUrl)
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun <T> build(block: (Retrofit) -> T): T {
        return block(retrofit)
    }
}