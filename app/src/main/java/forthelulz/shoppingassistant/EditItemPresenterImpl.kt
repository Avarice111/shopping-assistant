package forthelulz.shoppingassistant

import android.content.Context

class EditItemPresenterImpl(var editItemView: EditItemView, var context: Context) : EditItemPresenter {

    override fun submit(name: String, price: Double,listId: Long) {
        AppDatabase(context).shoppingItemDAO().update(ShoppingItem(0,name, price, listId))
        editItemView.moveToNewActivity(
            ViewListActivity::class.java,
            longArrayOf(listId)

        )
    }

    override fun loadAllByListId(shoppingListId: Long) {
        var shoppingItems:List<ShoppingItem> = AppDatabase(context).shoppingItemDAO()
            .loadAllByIds(longArrayOf(shoppingListId))
        editItemView.setList(shoppingItems)
    }
}

interface EditItemPresenter {

    fun submit(name:String, price: Double, listId: Long)
    fun loadAllByListId(shoppingListId:Long)

}