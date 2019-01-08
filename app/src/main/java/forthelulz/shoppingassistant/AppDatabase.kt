package forthelulz.shoppingassistant

import android.content.Context
import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Room

@Database(entities = arrayOf(ShoppingList::class, ShoppingItem::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun shoppingListDAO(): ShoppingListDAO
    abstract fun shoppingItemDAO(): ShoppingItemDAO

    companion object {
        private var INSTANCE: AppDatabase? = null
        var TEST_DB: Boolean = false

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    if(TEST_DB) {
                        INSTANCE = Room.inMemoryDatabaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase::class.java
                        ).allowMainThreadQueries().build()
                    } else {
                        INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase::class.java, "shopping.db"
                        ).build()
                    }
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}