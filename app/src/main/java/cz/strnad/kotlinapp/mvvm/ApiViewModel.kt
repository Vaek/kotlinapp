package cz.strnad.kotlinapp.mvvm

import android.app.Application
import android.os.Bundle
import cz.strnad.kotlinapp.api.ApiBuilder
import cz.strnad.kotlinapp.api.ApiService
import cz.strnad.kotlinapp.api.Error
import cz.strnad.mvvm.BaseViewModel
import okhttp3.ResponseBody
import retrofit2.Converter

open class ApiViewModel(application: Application, bundle: Bundle) : BaseViewModel(application, bundle) {

    companion object {
        private val builder = ApiBuilder("http://example.mock.apiblueprint.cz")
        val api = builder.build { it.create(ApiService::class.java) }
        val errorConverter: Converter<ResponseBody, Error> = builder.build {
            it.responseBodyConverter(Error::class.java, arrayOfNulls<Annotation>(0))
        }
    }

}