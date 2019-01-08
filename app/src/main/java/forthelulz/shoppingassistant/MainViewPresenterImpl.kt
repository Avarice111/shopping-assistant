package forthelulz.shoppingassistant

class MainViewPresenterImpl(var mainView: MainView, var db: AppDatabase) : ListPresenter {

    override fun loadList(listId:Long){

        var shoppingLists:List<ShoppingList> = db.shoppingListDAO().getAll()
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
            db.shoppingListDAO().insertAll(ShoppingList(0,"")).toLongArray()
            )
    }

}