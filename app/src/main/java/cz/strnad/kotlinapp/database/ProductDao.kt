package cz.strnad.kotlinapp.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import cz.strnad.kotlinapp.api.Product
import io.reactivex.Flowable

@Dao
interface ProductDao {

    @Query("SELECT * FROM ${ProductTable.TABLE_NAME}")
    fun getAll(): Flowable<Product>

    @Insert(onConflict = REPLACE)
    fun insert(product: Product)

    @Query("DELETE FROM ${ProductTable.TABLE_NAME}")
    fun deleteAll()
}