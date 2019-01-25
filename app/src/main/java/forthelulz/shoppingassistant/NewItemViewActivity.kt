package forthelulz.shoppingassistant

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText

class NewItemViewActivity: AppCompatActivity(), NewItemView {

    private var newItemPresenter:NewItemViewPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_item)

        newItemPresenter = NewItemViewPresenterImpl(this,this)

        val newItemName = findViewById<EditText>(R.id.nameFormItem)
        val newItemPrice = findViewById<EditText>(R.id.priceFormItem)

        val submitNewItem = findViewById<Button>(R.id.submitNewItem)

        val listId = getIntent().getExtras().getLongArray(Environment.EXTRA_IDS).first()

        submitNewItem.setOnClickListener {
            newItemPresenter?.submit(newItemName.text.toString(),
                newItemPrice.text.toString().toDouble(),
                listId)
        }

    }

    override fun moveToNewActivity(cls: Class<*>, ids: LongArray) {
        val intent = Intent(this, cls)
        intent.putExtra(Environment.EXTRA_IDS, ids)
        startActivity(intent)
    }

}

interface NewItemView {

    fun moveToNewActivity(cls: Class<*>, ids: LongArray)

}