package forthelulz.shoppingassistant

import android.content.Context

class NewItemViewPresenterImpl(var newListView: NewItemView, var context: Context) : NewItemViewPresenter {

    override fun submit(name: String, price: Int,listId: Long) {
        newListView.moveToNewActivity(
            ViewListActivity::class.java,
            AppDatabase(context).shoppingItemDAO().insertAll(ShoppingItem(0,name, price, listId))
                .foldRight(longArrayOf(), {id, outList -> outList.plus(id)})
        )
    }

}

interface NewItemViewPresenter {

    fun submit(name:String, price: Int, listId: Long)

}