package forthelulz.shoppingassistant

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.main.row_list_view.view.*

class ViewListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_list)

        val viewListView = findViewById<ListView>(R.id.viewListView)

        viewListView.adapter = CustomAdapter()

        //not working
        /*val layoutInflater = LayoutInflater.from(this)
        val buttonView: View = layoutInflater.inflate(R.layout.add_button_list, null)
        val footer = buttonView.findViewById<Button>(R.id.button2)
        viewListView.addFooterView(footer)*/

    }

    private class CustomAdapter: BaseAdapter() {

        private val names = arrayListOf<String>(
            "Cola", "Bread", "Pizza",
            "Chocolate"
        )

        private val prices = arrayListOf<Double>(
            2.99, 2.00, 14.99, 3.50
        )

        private val pricesSum = prices.sum()

        //responsible for how many rows are in my list
        override fun getCount(): Int {
            return names.size
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return "getItem"
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

            viewHolder.listNameView.text = names.get(position)
            viewHolder.listPriceView.text = prices.get(position).toString()


            return rowListView
        }


        //ViewHolder Pattern
        private class ViewHolder(val listNameView: TextView, val listPriceView: TextView)
    }
}
