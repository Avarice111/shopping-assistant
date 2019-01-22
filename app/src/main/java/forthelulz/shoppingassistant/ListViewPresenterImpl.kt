package forthelulz.shoppingassistant

import android.content.Context
import android.os.AsyncTask

class ListViewPresenterImpl(var listView: ShoppingListView, var context: Context) : ListViewPresenter {

    override fun loadList(listId:Long) {
        listView.setList(AppDatabase(context).shoppingItemDAO().loadAllByListId(listId))
    }

    override fun addItem(listId: Long) {
        val db = AppDatabase(context)
        db.shoppingItemDAO().insertAll(ShoppingItem(0,"",0,listId))
        listView.setList(AppDatabase(context).shoppingItemDAO().loadAllByListId(listId))
    }

    override fun update(itemId: Long, name: String, price: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(itemId: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

interface ListViewPresenter {

    fun loadList(listId:Long)
    fun addItem(listId: Long)
    fun update(itemId: Long, name:String, price:Int)
    fun delete(itemId: Long)

}