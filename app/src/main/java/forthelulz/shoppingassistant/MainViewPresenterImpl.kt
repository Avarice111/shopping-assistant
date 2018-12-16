package forthelulz.shoppingassistant
import android.content.Intent

class MainViewPresenterImpl(var mainView: MainView) : ListPresenter {

    override fun loadList(listId:Int){

        var shoppingLists:List<ShoppingList> = mutableListOf(
            ShoppingList("Decsription 1", mutableListOf()),
            ShoppingList("Decsription 2", mutableListOf()),
            ShoppingList("Decsription 3", mutableListOf()),
            ShoppingList("Decsription 4", mutableListOf())
        )

        mainView.setList(shoppingLists)

    }
    override fun loadItem(itemId:Int){

    }
    override fun addItem(){
        mainView.moveToNewActivity(ViewListActivity::class.java)
    }

}