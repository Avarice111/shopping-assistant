package forthelulz.shoppingassistant

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AppDatabase(context: Context) : SQLiteOpenHelper(context, AppDatabase.DB_NAME, null, AppDatabase.DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {

        if(!checkIfTableExists(TABLE_SHOPPING_LIST,db)) {
            val CREATE_SHOPPING_LIST = "CREATE TABLE $TABLE_SHOPPING_LIST ($ID_SHOPPING_LIST INTEGER PRIMARY KEY, $TITLE_SHOPPING_LIST TEXT);"
            db.execSQL(CREATE_SHOPPING_LIST)
        }

        if(!checkIfTableExists(TABLE_SHOPPING_ITEM,db)) {
            val CREATE_SHOPPING_ITEM = "CREATE TABLE $TABLE_SHOPPING_ITEM ($ID_SHOPPING_ITEM INTEGER PRIMARY KEY, $NAME_SHOPPING_ITEM TEXT, $PRICE_SHOPPING_ITEM DECIMAL(10,2), $LIST_ID_SHOPPING_ITEM INTEGER, FOREIGN KEY($LIST_ID_SHOPPING_ITEM) REFERENCES $TABLE_SHOPPING_LIST($ID_SHOPPING_LIST));"
            db.execSQL(CREATE_SHOPPING_ITEM)
        }

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val DROP_SHOPPING_LIST = "DROP TABLE IF EXISTS $TABLE_SHOPPING_LIST"
        val DROP_SHOPPING_ITEM = "DROP TABLE IF EXISTS $TABLE_SHOPPING_ITEM"
        db.execSQL(DROP_SHOPPING_ITEM)
        db.execSQL(DROP_SHOPPING_LIST)
        onCreate(db)
    }

    fun checkIfTableExists(tableName: String, db: SQLiteDatabase): Boolean {
        val cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '$tableName'", null)
        if (cursor != null) {
            if (cursor.count > 0) {
                cursor.close()
                return true
            }
            cursor.close()
        }
        return false
    }


    fun shoppingListDAO(): ShoppingListDAO {
        return object : ShoppingListDAO {
            override fun getAll(): List<ShoppingList> {
                val selectQuery = "SELECT * FROM $TABLE_SHOPPING_LIST"

                return getLists(selectQuery)
            }

            override fun loadAllByIds(listIds: LongArray): List<ShoppingList> {
                var selectQuery = "SELECT * FROM $TABLE_SHOPPING_LIST WHERE"

                if(listIds.isEmpty())
                    return ArrayList<ShoppingList>()

                for((index, id) in listIds.withIndex()) {
                    selectQuery += " $ID_SHOPPING_LIST=$id"
                    if(index < listIds.size - 1)
                        selectQuery += " OR"
                }

                return getLists(selectQuery)
            }

            override fun insertAll(vararg lists: ShoppingList): List<Long> {
                val db = writableDatabase
                val ids = arrayListOf<Long>() as MutableList<Long>

                db.beginTransaction()
                for(list in lists) {
                    val values = ContentValues()
                    values.put(TITLE_SHOPPING_LIST, list.title)
                    ids.add(db.insert(TABLE_SHOPPING_LIST, null, values))
                }
                db.endTransaction()
                return ids
            }

            override fun delete(item: ShoppingList) {

                val db = writableDatabase

                db.delete(TABLE_SHOPPING_LIST, " $ID_SHOPPING_LIST=?", arrayOf(item.id.toString()))
            }

            private fun getLists(query:String): List<ShoppingList> {
                val shoppingLists = ArrayList<ShoppingList>()
                val db = readableDatabase
                val cursor = db.rawQuery(query, null)
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        do {
                            val list = ShoppingList(
                                cursor.getLong(cursor.getColumnIndex(ID_SHOPPING_LIST)),
                                cursor.getString(cursor.getColumnIndex(TITLE_SHOPPING_LIST))
                            )
                            shoppingLists.add(list)
                        } while (cursor.moveToNext())
                    }
                    cursor.close()
                }
                return shoppingLists
            }
        }
    }
    fun shoppingItemDAO(): ShoppingItemDAO {
        return object : ShoppingItemDAO {
            override fun getAll(): List<ShoppingItem> {
                val selectQuery = "SELECT * FROM $TABLE_SHOPPING_ITEM"

                return getLists(selectQuery)
            }

            override fun loadAllByIds(itemIds: LongArray): List<ShoppingItem> {
                var selectQuery = "SELECT * FROM $TABLE_SHOPPING_ITEM WHERE"

                if(itemIds.isEmpty())
                    return ArrayList()

                for((index, id) in itemIds.withIndex()) {
                    selectQuery += " $ID_SHOPPING_ITEM=$id"
                    if(index < itemIds.size - 1)
                        selectQuery += " OR"
                }

                return getLists(selectQuery)
            }

            override fun loadAllByListId(shoppingListId: Long): List<ShoppingItem> {
                val selectQuery = "SELECT * FROM $TABLE_SHOPPING_ITEM WHERE $LIST_ID_SHOPPING_ITEM=$shoppingListId"

                return getLists(selectQuery)
            }

            override fun insertAll(vararg items: ShoppingItem): List<Long> {
                val db = writableDatabase
                val ids = arrayListOf<Long>() as MutableList<Long>

                db.beginTransaction()
                for(item in items) {
                    val values = ContentValues()
                    values.put(NAME_SHOPPING_ITEM, item.name)
                    values.put(PRICE_SHOPPING_ITEM, item.price)
                    values.put(LIST_ID_SHOPPING_ITEM, item.shoppingListId)
                    ids.add(db.insert(TABLE_SHOPPING_ITEM, null, values))
                }
                db.endTransaction()
                return ids
            }

            override fun delete(item: ShoppingItem) {

                val db = writableDatabase

                db.delete(TABLE_SHOPPING_ITEM, " $ID_SHOPPING_ITEM=?", arrayOf(item.id.toString()))
            }

            private fun getLists(query:String): List<ShoppingItem> {
                val shoppingLists = ArrayList<ShoppingItem>()
                val db = readableDatabase
                val cursor = db.rawQuery(query, null)
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        do {
                            val list = ShoppingItem(
                                cursor.getLong(cursor.getColumnIndex(ID_SHOPPING_ITEM)),
                                cursor.getString(cursor.getColumnIndex(NAME_SHOPPING_ITEM)),
                                cursor.getInt(cursor.getColumnIndex(PRICE_SHOPPING_ITEM)),
                                cursor.getLong(cursor.getColumnIndex(LIST_ID_SHOPPING_ITEM))
                            )
                            shoppingLists.add(list)
                        } while (cursor.moveToNext())
                    }
                    cursor.close()
                }
                return shoppingLists
            }
        }
    }

    companion object {
        private val DB_VERSION = 1
        private val DB_NAME = "Shopping"
        private val TABLE_SHOPPING_LIST = "ShoppingList"
        private val ID_SHOPPING_LIST = "Id"
        private val TITLE_SHOPPING_LIST = "Title"
        private val TABLE_SHOPPING_ITEM = "ShoppingItem"
        private val ID_SHOPPING_ITEM = "Id"
        private val NAME_SHOPPING_ITEM = "Name"
        private val PRICE_SHOPPING_ITEM = "Price"
        private val LIST_ID_SHOPPING_ITEM = "ShoppingListId"
    }
}