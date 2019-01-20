package forthelulz.shoppingassistant

import android.content.Context
import  android.os.AsyncTask

class MainViewPresenterImpl(var mainView: MainView, var context: Context) : ListPresenter {

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
            ViewListActivity::class.java,
            AppDatabase(context).shoppingListDAO().insertAll(ShoppingList(0,""))
                .foldRight(longArrayOf(), {id, outList -> outList.plus(id)})
        )
    }

    private class AsyncLoadList(var context: Context): AsyncTask<Unit, Unit, List<ShoppingList>>() {

        override fun doInBackground(vararg params: Unit): List<ShoppingList>? {
            return AppDatabase(context).shoppingListDAO().getAll()
        }


    }

    private class AsyncAddItem(var context: Context): AsyncTask<ShoppingList, Unit, List<Long>>() {

        override fun doInBackground(vararg params: ShoppingList): List<Long>? {
            var out:List<Long> = mutableListOf()
            var db = AppDatabase(context)
            for(l in params)
                out += db.shoppingListDAO().insertAll(l)
            return out
        }


    }

}