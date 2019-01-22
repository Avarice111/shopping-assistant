package forthelulz.shoppingassistant

import android.content.Context
import  android.os.AsyncTask

class MainViewPresenterImpl(var mainView: MainView, var context: Context) : MainViewPresenter {

    override fun loadList(listId:Long){

        var shoppingLists:List<ShoppingList> = AppDatabase(context).shoppingListDAO().getAll()
        mainView.setList(shoppingLists)

    }
    override fun loadItem(itemId:Long){
        mainView.moveToNewActivity(
            ViewListActivity::class.java,
            longArrayOf(itemId)
        )

    }
    override fun addItem(){
        mainView.moveToNewActivity(
            NewListViewActivity::class.java,
            longArrayOf()
        )
    }

    override fun update(itemId: Long, name: String) {
        AppDatabase(context).shoppingListDAO().update(ShoppingList(itemId, name))
    }

    override fun delete(itemId: Long) {
        val listDao = AppDatabase(context).shoppingListDAO()
        listDao.delete(itemId)
        mainView.setList(listDao.getAll())
    }

}

interface MainViewPresenter {

    fun loadList(listId:Long)
    fun loadItem(itemId:Long)
    fun addItem()
    fun update(itemId:Long, name:String)
    fun delete(itemId: Long)

}