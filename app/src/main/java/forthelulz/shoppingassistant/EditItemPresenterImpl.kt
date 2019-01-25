package forthelulz.shoppingassistant

import android.content.Context

class EditItemPresenterImpl(var editItemView: EditItemView, var context: Context) : EditItemPresenter {

    override fun submit(itemId: Long, name: String, price: Double, listId: Long) {
        AppDatabase(context).shoppingItemDAO().update(ShoppingItem(itemId,name, price, listId))
        editItemView.moveToNewActivity(
            ViewListActivity::class.java,
            longArrayOf(listId)

        )
    }

    override fun loadItemById(itemId: Long) {
        var shoppingItems:List<ShoppingItem> = AppDatabase(context).shoppingItemDAO()
            .loadAllByIds(longArrayOf(itemId))
        editItemView.setList(shoppingItems)
    }
}

interface EditItemPresenter {

    fun submit(itemId: Long, name: String, price: Double, listId: Long)
    fun loadItemById(itemId:Long)

}