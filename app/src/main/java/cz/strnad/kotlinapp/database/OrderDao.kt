package cz.strnad.kotlinapp.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import cz.strnad.kotlinapp.api.Order
import cz.strnad.kotlinapp.api.Product
import io.reactivex.Flowable

@Dao
interface OrderDao {

    @Query("SELECT * FROM ${OrderTable.TABLE_NAME} WHERE ${OrderTable.COL_USER_ID} = :userId")
    fun getAll(userId: String): Flowable<List<Order>>

    @Insert(onConflict = REPLACE)
    fun insert(order: Order)

    @Query("DELETE FROM ${OrderTable.TABLE_NAME} WHERE ${OrderTable.COL_USER_ID} = :userId")
    fun deleteAll(userId: String)
}