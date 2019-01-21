package forthelulz.shoppingassistant

import android.content.Context
import android.os.AsyncTask

class ListViewPresenterImpl(var listView: ShoppingListView, var context: Context) : ListPresenter {

    override fun loadList(listId:Long) {

        var itemList:List<ShoppingItem> = AppDatabase(context).shoppingItemDAO().loadAllByListId(listId)
        listView.setList(itemList)
    }
    override fun loadItem(itemId:Long) {

    }
    override fun addItem() {

    }

    private class AsyncLoadListWithIds(var context: Context): AsyncTask<Long, Unit, List<ShoppingItem>>() {

        override fun doInBackground(vararg params: Long?): List<ShoppingItem>? {
            var out:List<ShoppingItem> = mutableListOf()
            var db = AppDatabase(context)
            for(l in params)
                out += db.shoppingItemDAO().loadAllByListId(l!!)
            return out
        }


    }

}