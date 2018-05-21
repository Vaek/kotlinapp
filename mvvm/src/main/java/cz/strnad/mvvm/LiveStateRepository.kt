package cz.strnad.mvvm

/**
 * Created by vaclavstrnad on 26/04/2018.
 */

open class LiveStateRepository<T, E> : Repository() {

    val state = LiveState<T, E>()

}