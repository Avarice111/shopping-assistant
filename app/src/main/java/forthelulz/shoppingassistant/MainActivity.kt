package forthelulz.shoppingassistant

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import kotlinx.android.synthetic.main.row_list_main.view.*
import android.widget.Toast


class MainActivity : AppCompatActivity(), MainView {

    private val adapter:CustomAdapter = CustomAdapter()

    private var shoppingLists:List<ShoppingList> = mutableListOf()

    private var mainPresenter:ListPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainPresenter = MainViewPresenterImpl(this,AppDatabase(this))

        val listView = findViewById<ListView>(R.id.mainListView)
        listView.adapter = adapter

        val addListButton = findViewById<ImageButton>(R.id.addListButton)

        mainPresenter?.loadList(0)

        addListButton.setOnClickListener {
            mainPresenter?.addItem()
        }

        listView.onItemClickListener = object : OnItemClickListener {

            override fun onItemClick(parent: AdapterView<*>, view: View,
                                     position: Int, id: Long) {

                mainPresenter?.loadItem(listView.getItemIdAtPosition(position))
            }
        }
    }

    override fun setList(list: List<ShoppingList>) {
        shoppingLists = list
        adapter.setList(list)
    }
    override fun moveToNewActivity(cls: Class<*>, ids: LongArray) {
        val intent = Intent(this, cls)
        intent.putExtra(Environment.EXTRA_IDS, ids)
        startActivity(intent)
    }

    private class CustomAdapter: BaseAdapter() {

        private var shoppingLists:List<ShoppingList> = mutableListOf()

        fun setList(list: List<ShoppingList>) {
            shoppingLists = list;
        }

        //responsible for how many rows are in my list
        override fun getCount(): Int {
            return shoppingLists.size
        }

        override fun getItemId(position: Int): Long {
            return shoppingLists.get(position).id
        }

        override fun getItem(position: Int): Any {
            return shoppingLists.get(position)
        }

        //responsible for rendering out each row
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            //ViewHolder Pattern used for optimalization
            val rowListMain: View

            if (convertView == null) {
                val layoutInflater = LayoutInflater.from(parent!!.context)
                rowListMain = layoutInflater.inflate(R.layout.row_list_main, parent, false)

                val viewHolder = ViewHolder(rowListMain.listNameView, rowListMain.listDescriptionMain)
                rowListMain.tag = viewHolder
            }
            else {
                rowListMain = convertView
            }

            val viewHolder = rowListMain.tag as ViewHolder

            viewHolder.listNameMain.text = shoppingLists.get(position).title
            viewHolder.listDescriptionMain.text = shoppingLists.get(position).title


            return rowListMain
        }

        //ViewHolder Pattern
        private class ViewHolder(val listNameMain: TextView, val listDescriptionMain: TextView)
    }
}

interface MainView {

    fun setList(list: List<ShoppingList>)
    fun moveToNewActivity(cls: Class<*>, ids: LongArray)

}
