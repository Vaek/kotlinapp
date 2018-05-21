package cz.strnad.kotlinapp.mvvm

import cz.strnad.kotlinapp.api.Error
import cz.strnad.kotlinapp.api.Translator
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

class CustomErrorProcessor(private val errorConverter: Converter<ResponseBody, Error>, private val translat: Translator<Error>) : SingleLiveState.ErrorProcessor<Error> {

    override fun process(e: Throwable): Error {
        if (e is HttpException) {
            return e.response().errorBody()
                    .let { errorConverter.convert(it) }
                    .apply { this.translator = translat }
        } else if (e is SocketTimeoutException) {
            return Error.TIMEOUT
        } else if (e is IOException) {
            return Error.DISCONNECTED
        } else {
            return Error.UNKNOWN
        }
    }

}