package cz.strnad.kotlinapp.database

object UserTable {
    const val TABLE_NAME = "user"
    const val COL_ID = "_id"
    const val COL_NAME = "name"
    const val COL_EMAIL = "email"
}

object ProductTable {
    const val TABLE_NAME = "product"
    const val COL_ID = "_id"
    const val COL_NAME = "name"
    const val COL_PRICE = "price"
    const val COL_CATEGORY_ID = "categoryId"
}

object OrderTable {
    /**
     * {@value "order"} reserved word
     */
    const val TABLE_NAME = "orderTable"
    const val COL_ID = "_id"
    const val COL_USER_ID = "userId"
    const val COL_PRODUCT_ID = "productId"
    const val COL_COUNT = "count"
}

object CategoryTable {
    const val TABLE_NAME = "category"
    const val COL_ID = "_id"
    const val COL_NAME = "name"
    const val COL_PARENT_CATEGORY_ID = "parentCategoryId"
}