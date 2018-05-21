package cz.strnad.kotlinapp.api

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import cz.strnad.kotlinapp.database.CategoryTable
import cz.strnad.kotlinapp.database.OrderTable
import cz.strnad.kotlinapp.database.ProductTable
import cz.strnad.kotlinapp.database.UserTable
import java.math.BigDecimal

@Entity(tableName = UserTable.TABLE_NAME)
data class User(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = UserTable.COL_ID) var id: String,
                @ColumnInfo(name = UserTable.COL_EMAIL) var email: String,
                @ColumnInfo(name = UserTable.COL_NAME) var name: String) {

    constructor() : this("", "", "")
}

@Entity(tableName = ProductTable.TABLE_NAME)
data class Product(@PrimaryKey @ColumnInfo(name = ProductTable.COL_ID) var id: String,
                   @ColumnInfo(name = ProductTable.COL_NAME) var name: String,
                   @ColumnInfo(name = ProductTable.COL_PRICE) var price: BigDecimal,
                   @ColumnInfo(name = ProductTable.COL_CATEGORY_ID) var categoryId: Long) {

    constructor() : this("", "", BigDecimal.ZERO, 0)
}

@Entity(tableName = OrderTable.TABLE_NAME)
data class Order(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = OrderTable.COL_ID) var id: String,
                 @ColumnInfo(name = OrderTable.COL_USER_ID) var userId: String,
                 @ColumnInfo(name = OrderTable.COL_PRODUCT_ID) var productId: Long,
                 @ColumnInfo(name = OrderTable.COL_COUNT) var count: Double) {

    constructor() : this("", "", 0, 0.0)
}

@Entity(tableName = CategoryTable.TABLE_NAME)
data class Category(@PrimaryKey @ColumnInfo(name = CategoryTable.COL_ID) var id: String,
                    @ColumnInfo(name = CategoryTable.COL_NAME) var name: String,
                    @ColumnInfo(name = CategoryTable.COL_PARENT_CATEGORY_ID) var parentCategoryId: Long) {

    constructor() : this("", "", 0)
}

open class Error(var code: Int, var message: String) {

    companion object {
        val UNKNOWN = Error(-1, "")
        val TIMEOUT = Error(-2, "")
        val DISCONNECTED = Error(-3, "")
    }

    constructor() : this(0, "")

    var translator: Translator<Error>? = null

    fun getTranslation(): String? {
        return translator?.translate(this)
    }
}

data class LoginRequest(var username: String, var password: String) {

    constructor() : this("", "")
}

data class LoginResponse(var user: User, var token: String) {

    constructor() : this(User(), "")
}