package forthelulz.shoppingassistant

import android.content.Context
import android.content.Intent
import android.os.AsyncTask

class ListViewPresenterImpl(var listView: ShoppingListView, var context: Context) : ListViewPresenter {

    override fun loadList(listId:Long) {
        listView.setList(AppDatabase(context).shoppingItemDAO().loadAllByListId(listId))
    }

    override fun addItem(listId: Long) {
        /*val db = AppDatabase(context)
        db.shoppingItemDAO().insertAll(ShoppingItem(0,"",0,listId))
        listView.setList(AppDatabase(context).shoppingItemDAO().loadAllByListId(listId))*/
        listView.moveToNewActivity(
            NewItemViewActivity::class.java,
            longArrayOf(listId)
        )
    }

    override fun update(itemId: Long) {
        listView.moveToNewActivity(
            EditItemViewActivity::class.java,
            longArrayOf(itemId)
        )
    }

    override fun delete(itemId: Long) {
        val itemDAO = AppDatabase(context).shoppingItemDAO()
        itemDAO.delete(itemId)
        listView.setList(itemDAO.getAll())
    }

}

interface ListViewPresenter {

    fun loadList(listId:Long)
    fun addItem(listId: Long)
    fun update(itemId: Long)
    fun delete(itemId: Long)

}