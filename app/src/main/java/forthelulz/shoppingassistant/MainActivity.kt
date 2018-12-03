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
import kotlinx.android.synthetic.main.row_list_main.view.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addListButton = findViewById<ImageButton>(R.id.addListButton)

        addListButton.setOnClickListener {
            val intent = Intent(this, ViewListActivity :: class.java)
            startActivity(intent)
        }

        val listView = findViewById<ListView>(R.id.mainListView)

        listView.adapter = CustomAdapter()
    }

    private class CustomAdapter: BaseAdapter() {

        private val names = arrayListOf<String>(
            "Shopping", "Grocery Store", "New Way of Spending Money",
            "How much am i going to spend?"
        )

        private val descriptions = arrayListOf<String>(
            "Description 1", "Description 2", "Description 3", "Description 4"
        )

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

            viewHolder.listNameMain.text = names.get(position)
            viewHolder.listDescriptionMain.text = descriptions.get(position)


            return rowListMain
        }

        //ViewHolder Pattern
        private class ViewHolder(val listNameMain: TextView, val listDescriptionMain: TextView)
    }
}
