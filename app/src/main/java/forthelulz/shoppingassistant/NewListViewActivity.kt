package forthelulz.shoppingassistant

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText

class NewListViewActivity: AppCompatActivity(), NewListView {

    private var newListPresenter:NewListViewPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_shopping_list)

        newListPresenter = NewListViewPresenterImpl(this,this)

        val newListName = findViewById<EditText>(R.id.newListName)

        val submitNewList = findViewById<Button>(R.id.submitNewList)

        submitNewList.setOnClickListener {
            newListPresenter?.submit(newListName.text.toString())
        }

    }

    override fun moveToNewActivity(cls: Class<*>, ids: LongArray) {
        val intent = Intent(this, cls)
        intent.putExtra(Environment.EXTRA_IDS, ids)
        startActivity(intent)
    }

}

interface NewListView {

    fun moveToNewActivity(cls: Class<*>, ids: LongArray)

}