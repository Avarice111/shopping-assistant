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

    override fun update(itemId: Long, name: String, price: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
     * updates item with given id and refreshes activity
     * @param itemId item id
     * @param name new name for item
     * @param price new price for item
     */
    fun update(itemId: Long, name:String, price:Int)
    /**
     * deletes item with given id and refreshes activity
     * @param itemId item id
     */
    fun delete(itemId: Long)

}