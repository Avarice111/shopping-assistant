package forthelulz.shoppingassistant

import android.content.Context

class NewItemViewPresenterImpl(var newListView: NewItemView, var context: Context) : NewItemViewPresenter {

    override fun submit(name: String, price: Double,listId: Long) {
        AppDatabase(context).shoppingItemDAO().insertAll(ShoppingItem(0,name, price, listId))
        newListView.moveToNewActivity(
            ViewListActivity::class.java,
                longArrayOf(listId)

        )
    }

}

interface NewItemViewPresenter {

    fun submit(name:String, price: Double, listId: Long)

}