package cz.strnad.kotlinapp.api

/**
 * Created by vaclavstrnad on 25/04/2018.
 */
interface Translator {

    fun translate(error: Error): String
}