package cz.strnad.kotlinapp.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import cz.strnad.kotlinapp.api.Category
import cz.strnad.kotlinapp.api.Order
import cz.strnad.kotlinapp.api.Product

@Database(entities = arrayOf(Category::class, Product::class, Order::class),
        version = 1)
@TypeConverters(Converters::class)
abstract class KotlinAppDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao
    abstract fun orderDao(): OrderDao

    companion object {
        private var INSTANCE: KotlinAppDatabase? = null

        fun getInstance(context: Context): KotlinAppDatabase {
            if (INSTANCE == null) {
                synchronized(KotlinAppDatabase::class) {
                    INSTANCE = INSTANCE ?: Room.databaseBuilder(
                            context.applicationContext,
                            KotlinAppDatabase::class.java,
                            "kotlinapp.db")
                            .build()
                }
            }

            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}