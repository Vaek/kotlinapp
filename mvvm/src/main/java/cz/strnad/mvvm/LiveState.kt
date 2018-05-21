package cz.strnad.mvvm

import android.arch.lifecycle.MutableLiveData

/**
 * Created by vaclavstrnad on 14/05/2018.
 */
open class LiveState<T, E> {

    val loading: MutableLiveData<Boolean> = MutableLiveData()

    val data: MutableLiveData<T> = MutableLiveData()

    val error: MutableLiveData<E> = MutableLiveData()
}