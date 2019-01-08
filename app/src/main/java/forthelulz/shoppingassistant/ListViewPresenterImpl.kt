package forthelulz.shoppingassistant

class ListViewPresenterImpl(var listView: ShoppingListView, var db: AppDatabase) : ListPresenter {

    override fun loadList(listId:Long) {

        var itemList:List<ShoppingItem> = db.shoppingItemDAO().loadAllByListId(listId)
        listView.setList(itemList)
    }
    override fun loadItem(itemId:Long) {

    }
    override fun addItem() {

    }

}