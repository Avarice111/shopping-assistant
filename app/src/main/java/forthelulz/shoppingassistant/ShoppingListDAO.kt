package forthelulz.shoppingassistant

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Delete

@Dao
interface ShoppingListDAO {
    @Query("SELECT * FROM ShoppingList")
    fun getAll(): List<ShoppingList>

    @Query("SELECT * FROM ShoppingList WHERE id IN (:listIds)")
    fun loadAllByIds(listIds: LongArray): List<ShoppingList>

    @Insert
    fun insertAll(vararg lists: ShoppingList): List<Long>


    @Delete
    fun delete(items: ShoppingList)

}

@Dao
interface ShoppingItemDAO {
    @Query("SELECT * FROM ShoppingItem")
    fun getAll(): List<ShoppingItem>

    @Query("SELECT * FROM ShoppingItem WHERE id IN (:itemIds)")
    fun loadAllByIds(itemIds: LongArray): List<ShoppingItem>

    @Query("SELECT * FROM ShoppingItem WHERE list_id == :shoppingListId")
    fun loadAllByListId(shoppingListId:Long): List<ShoppingItem>

    @Insert
    fun insertAll(vararg items: ShoppingItem): List<Long>

    @Delete
    fun delete(items: ShoppingItem)
}