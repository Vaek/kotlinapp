package cz.strnad.kotlinapp.mvvm

import cz.strnad.kotlinapp.api.ApiService
import cz.strnad.kotlinapp.api.Order
import cz.strnad.kotlinapp.api.User
import cz.strnad.kotlinapp.database.KotlinAppDatabase
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by vaclavstrnad on 24/04/2018.
 */
class OrderRepository(api: ApiService,
                      database: KotlinAppDatabase) : OfflineApiRepository(api, database) {

    internal fun getOrders(user: User?): Single<List<Order>> {
        return database.orderDao().getAll(user?.id ?: "")
                .flatMap({ t: List<Order> ->
                    if (t.isEmpty()) {
                        api.getOrders(user?.id ?: "").toFlowable()
                    } else {
                        Flowable.just(t)
                    }
                })
                .single(ArrayList())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }
}
