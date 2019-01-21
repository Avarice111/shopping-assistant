package forthelulz.shoppingassistant

data class ShoppingList(
    val id:Long,
    val title:String
)

data class ShoppingItem(
    val id:Long,
    val name:String,
    val price:Int,
    val shoppingListId:Long
)