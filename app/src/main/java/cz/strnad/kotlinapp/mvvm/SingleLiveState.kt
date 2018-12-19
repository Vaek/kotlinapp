package cz.strnad.kotlinapp.mvvm

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import cz.strnad.mvvm.LiveState
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver

/**
 * Created by vaclavstrnad on 14/05/2018.
 */
open class SingleLiveState<T, E> : LiveState<T, E>() {

    var errorProcessor: ErrorProcessor<E>? = null

    private var observer: DisposableSingleObserver<T>? = null

    fun subscribe(orders: Single<T>) {
        dispose()
        observer = orders.doOnSubscribe { loading.value = true }
                .doFinally { loading.value = false }
                .subscribeWith(object : DisposableSingleObserver<T>() {
                    override fun onSuccess(t: T) {
                        data.value = t
                    }

                    override fun onError(e: Throwable) {
                        error.value = errorProcessor?.process(e)
                    }
                })
    }

    fun dispose() {
        observer?.dispose()
    }

    interface ErrorProcessor<E> {
        fun process(e: Throwable): E
    }

}