package cz.strnad.kotlinapp.mvvm

import cz.strnad.kotlinapp.api.ApiService
import cz.strnad.kotlinapp.api.Error
import cz.strnad.kotlinapp.api.Order
import cz.strnad.kotlinapp.api.User
import cz.strnad.kotlinapp.database.KotlinAppDatabase
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Converter

/**
 * Created by vaclavstrnad on 24/04/2018.
 */
class OrderRepository(api: ApiService,
                      errorConverter: Converter<ResponseBody, Error>,
                      database: KotlinAppDatabase) : OfflineApiRepository<List<Order>>(api, errorConverter, database) {

    internal fun getOrders(user: User?) {
        user?.let {
            database.orderDao().getAll(it.id)
                    .flatMap({ t: List<Order> ->
                        if (t.isEmpty()) {
                            api.getOrders(user.id).toFlowable()
                        } else {
                            Flowable.just(t)
                        }
                    })
                    .single(ArrayList())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .doOnSubscribe { loading.value = true }
                    .doFinally { loading.value = false }
                    .subscribeWith(object : DisposableSingleObserver<List<Order>>() {
                        override fun onSuccess(t: List<Order>) {
                            data.value = t
                        }

                        override fun onError(e: Throwable) {
                            processError(e)
                        }
                    })
        }
    }
}
