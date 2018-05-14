package cz.strnad.kotlinapp.api

import android.content.Context
import cz.strnad.kotlinapp.R

/**
 * Created by vaclavstrnad on 26/04/2018.
 */
class DefaultTranslator(private val context: Context) : Translator<Error> {

    override fun translate(error: Error): String {
        return when (error.code) {
            -3 -> context.getString(R.string.connection_disconnected)
            -2 -> context.getString(R.string.connection_timeout)
            -1 -> context.getString(R.string.unknown_error)
            else -> context.getString(R.string.unknown_error)
        }
    }
}