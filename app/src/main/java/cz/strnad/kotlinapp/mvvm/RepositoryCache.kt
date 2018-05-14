package cz.strnad.kotlinapp.mvvm

import android.arch.lifecycle.MutableLiveData

/**
 * Created by vaclavstrnad on 14/05/2018.
 */
class RepositoryCache<T, E> {

    val loading: MutableLiveData<Boolean> = MutableLiveData()

    val data: MutableLiveData<T> = MutableLiveData()

    val error: MutableLiveData<E> = MutableLiveData()
}