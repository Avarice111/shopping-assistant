package forthelulz.shoppingassistant

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_new_item.*

class EditItemViewActivity: AppCompatActivity(), EditItemView {

    private var editItemPresenter: EditItemPresenter? = null

    private var shoppingItems:List<ShoppingItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_item)

        editItemPresenter = EditItemPresenterImpl(this,this)

        val nameFormItem = findViewById<EditText>(R.id.nameFormItem)

        val priceFormItem = findViewById<EditText>(R.id.priceFormItem)

        val submitEditItem = findViewById<Button>(R.id.submitEditItem)



        val itemId = getIntent().getExtras().getLongArray(Environment.EXTRA_IDS).first()

        editItemPresenter?.loadItemById(itemId)

        submitEditItem.setOnClickListener {
            editItemPresenter?.submit(
                itemId,
                nameFormItem.text.toString(),
                priceFormItem.text.toString().toDouble(),
                shoppingItems.first().shoppingListId
            )
        }

    }

    override fun setList(list: List<ShoppingItem>) {
        shoppingItems = list
        /*adapter.setList(list)
        adapter.notifyDataSetChanged()*/
        nameFormItem.setText(shoppingItems.first().name)
        priceFormItem.setText(shoppingItems.first().price.toString())
    }

    override fun moveToNewActivity(cls: Class<*>, ids: LongArray) {
        val intent = Intent(this, cls)
        intent.putExtra(Environment.EXTRA_IDS, ids)
        startActivity(intent)
    }

}

interface EditItemView {

    fun moveToNewActivity(cls: Class<*>, ids: LongArray)
    fun setList(list: List<ShoppingItem>)
}