package forthelulz.shoppingassistant

class ListViewPresenterImpl(var listView: ShoppingListView) : ListPresenter {

    override fun loadList(listId:Int) {

        var itemList:List<ShoppingItem> = mutableListOf(
            ShoppingItem("item 1", 100),
            ShoppingItem("item 2", 200),
            ShoppingItem("item 3", 300),
        )

        listView.setList(itemList)
    }
    override fun loadItem(itemId:Int) {

    }
    override fun addItem() {

    }

}