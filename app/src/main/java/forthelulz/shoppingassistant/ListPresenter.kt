package forthelulz.shoppingassistant

interface ListPresenter {

    fun loadList(listId:Long)
    fun loadItem(itemId:Long)
    fun addItem()

}