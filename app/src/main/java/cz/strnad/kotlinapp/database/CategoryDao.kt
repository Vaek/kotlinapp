package cz.strnad.kotlinapp.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import cz.strnad.kotlinapp.api.Category
import io.reactivex.Flowable

@Dao
interface CategoryDao {

    @Query("SELECT * FROM ${CategoryTable.TABLE_NAME}")
    fun getAll(): Flowable<Category>

    @Insert(onConflict = REPLACE)
    fun insert(product: Category)

    @Query("DELETE FROM ${CategoryTable.TABLE_NAME}")
    fun deleteAll()
}