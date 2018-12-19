package cz.strnad.mvvm

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer

/**
 * Created by vaclavstrnad on 14/05/2018.
 */
open class LiveState<T, E> {

    val loading: MutableLiveData<Boolean> = MutableLiveData()

    val data: MutableLiveData<T> = MutableLiveData()

    val error: MutableLiveData<E> = MutableLiveData()

    fun observe(owner: LifecycleOwner, observer: StateObserver<T, E>) {
        loading.observe(owner, Observer { observer.onLoadingChange(it) })
        data.observe(owner, Observer { observer.onDataChange(it) })
        error.observe(owner, Observer { observer.onErrorChange(it) })
    }

    interface StateObserver<T, E> {
        fun onLoadingChange(loading: Boolean?)
        fun onDataChange(loading: T?)
        fun onErrorChange(loading: E?)
    }
}