package forthelulz.shoppingassistant

import android.os.AsyncTask

class ListViewPresenterImpl(var listView: ShoppingListView, var db: AppDatabase) : ListPresenter {

    override fun loadList(listId:Long) {

        var itemList:List<ShoppingItem> = AsyncLoadListWithIds(db).execute(listId).get()
        listView.setList(itemList)
    }
    override fun loadItem(itemId:Long) {

    }
    override fun addItem() {

    }

    private class AsyncLoadListWithIds(var db: AppDatabase): AsyncTask<Long, Unit, List<ShoppingItem>>() {

        override fun doInBackground(vararg params: Long?): List<ShoppingItem>? {
            var out:List<ShoppingItem> = mutableListOf()
            for(l in params)
                out += db.shoppingItemDAO().loadAllByListId(l!!)
            return out
        }


    }

}