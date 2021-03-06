package forthelulz.shoppingassistant

import android.content.Context
import android.content.Intent
import android.os.AsyncTask

/**
 * ListViewPresenter implementation
 */
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

/**
 * Presenter responsible for manipulating ViewListActivity
 */
interface ListViewPresenter {

    /**
     * loads all ShoppingItems from list with given id and pushes them into activity
     * @param listId id of ShoppingList
     */
    fun loadList(listId:Long)
    /**
     * adds new item to ShoppingList and refreshes activity
     * @param listId id of ShoppingList
     */
    fun addItem(listId: Long)
    /**
     * updates item with given id and starts edition activity
     * @param itemId item id
     */
    fun update(itemId: Long)
    /**
     * deletes item with given id and refreshes activity
     * @param itemId item id
     */
    fun delete(itemId: Long)

}