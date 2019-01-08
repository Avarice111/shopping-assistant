package forthelulz.shoppingassistant

import  android.os.AsyncTask

class MainViewPresenterImpl(var mainView: MainView, var db: AppDatabase) : ListPresenter {

    override fun loadList(listId:Long){

        var shoppingLists:List<ShoppingList> = AsyncLoadList(db).get()
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
            AsyncAddItem(db).execute(ShoppingList(0,"")).get().toLongArray()
            )
    }

    private class AsyncLoadList(var db: AppDatabase): AsyncTask<Unit, Unit, List<ShoppingList>>() {

        override fun doInBackground(vararg params: Unit): List<ShoppingList>? {
            return db.shoppingListDAO().getAll()
        }


    }

    private class AsyncAddItem(var db: AppDatabase): AsyncTask<ShoppingList, Unit, List<Long>>() {

        override fun doInBackground(vararg params: ShoppingList): List<Long>? {
            var out:List<Long> = mutableListOf()
            for(l in params)
                out += db.shoppingListDAO().insertAll(l)
            return out
        }


    }

}