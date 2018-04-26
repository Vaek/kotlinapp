package cz.strnad.kotlinapp.database

import android.arch.persistence.room.TypeConverter
import java.math.BigDecimal

/**
 * Created by vaclavstrnad on 20/04/2018.
 */
class Converters {

    @TypeConverter
    fun fromCurrency(value: String?): BigDecimal? {
        return if (value == null) null else BigDecimal(value)
    }

    @TypeConverter
    fun toCurrency(currency: BigDecimal?): String? {
        return currency?.toString()
    }
}