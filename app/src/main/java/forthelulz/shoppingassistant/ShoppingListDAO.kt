package forthelulz.shoppingassistant

interface ShoppingListDAO {
    fun getAll(): List<ShoppingList>

    fun loadAllByIds(listIds: LongArray): List<ShoppingList>

    fun insertAll(vararg lists: ShoppingList): List<Long>

    fun delete(item: ShoppingList)

}

interface ShoppingItemDAO {
    fun getAll(): List<ShoppingItem>

    fun loadAllByIds(itemIds: LongArray): List<ShoppingItem>

    fun loadAllByListId(shoppingListId:Long): List<ShoppingItem>

    fun insertAll(vararg items: ShoppingItem): List<Long>

    fun delete(item: ShoppingItem)
}