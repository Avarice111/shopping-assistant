package forthelulz.shoppingassistant

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.main.row_list_view.view.*

class ViewListActivity : AppCompatActivity(), ShoppingListView {

    private val adapter:CustomAdapter = CustomAdapter()

    private var shoppingItems:List<ShoppingItem> = mutableListOf()

    private var listPresenter:ListPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_list)

        listPresenter = ListViewPresenterImpl(this, AppDatabase.getInstance(this)!!)

        val viewListView = findViewById<ListView>(R.id.viewListView)

        viewListView.adapter = adapter

        listPresenter?.loadList(getIntent().getExtras().getLongArray(Environment.EXTRA_IDS).first())

        //not working
        /*val layoutInflater = LayoutInflater.from(this)
        val buttonView: View = layoutInflater.inflate(R.layout.add_button_list, null)
        val footer = buttonView.findViewById<Button>(R.id.button2)
        viewListView.addFooterView(footer)*/

    }

    override fun setList(list: List<ShoppingItem>) {
        shoppingItems = list;
        adapter.setList(shoppingItems)
    }

    private class CustomAdapter: BaseAdapter() {

        private var shoppingItems:List<ShoppingItem> = mutableListOf()

        fun setList(list: List<ShoppingItem>) {
            shoppingItems = list;
        }

        //responsible for how many rows are in my list
        override fun getCount(): Int {
            return shoppingItems.size
        }

        override fun getItemId(position: Int): Long {
            return shoppingItems.get(position).id
        }

        override fun getItem(position: Int): Any {
            return shoppingItems.get(position)
        }

        //responsible for rendering out each row
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            //ViewHolder Pattern used for optimalization
            val rowListView: View

            if (convertView == null) {
                val layoutInflater = LayoutInflater.from(parent!!.context)
                rowListView = layoutInflater.inflate(R.layout.row_list_view, parent, false)

                val viewHolder = ViewHolder(rowListView.listNameView, rowListView.listPriceView)
                rowListView.tag = viewHolder
            } else {
                rowListView = convertView
            }

            val viewHolder = rowListView.tag as ViewHolder

            viewHolder.listNameView.text = shoppingItems.get(position).name
            viewHolder.listPriceView.text = shoppingItems.get(position).price.toString()


            return rowListView
        }


        //ViewHolder Pattern
        private class ViewHolder(val listNameView: TextView, val listPriceView: TextView)
    }
}

interface ShoppingListView {

    fun setList(list: List<ShoppingItem>)

}