package forthelulz.shoppingassistant

import android.content.Context

class NewListViewPresenterImpl(var newListView: NewListView, var context: Context) : NewListViewPresenter {

    override fun submit(name: String) {
        newListView.moveToNewActivity(
            ViewListActivity::class.java,
            AppDatabase(context).shoppingListDAO().insertAll(ShoppingList(0,name))
                .foldRight(longArrayOf(), {id, outList -> outList.plus(id)})
        )
    }

}

interface NewListViewPresenter {

    fun submit(name:String)

}