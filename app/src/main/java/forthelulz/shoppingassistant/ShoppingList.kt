package forthelulz.shoppingassistant

/**
 * Entity representing shopping list in database
 * Also acts as foreign key for ShoppingItem
 */
data class ShoppingList(
    val id:Long,
    val title:String
)

/**
 * entity responsible for representing shopping item in database
 */
data class ShoppingItem(
    val id:Long,
    val name:String,
    val price:Double,
    val shoppingListId:Long
)