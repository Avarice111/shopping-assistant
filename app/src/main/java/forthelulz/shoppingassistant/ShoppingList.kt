package forthelulz.shoppingassistant

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.ForeignKey

@Entity
data class ShoppingList(
    @PrimaryKey val id:Long,
    val title:String
)

@Entity(foreignKeys = arrayOf(ForeignKey(
    entity = ShoppingList::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("list_id"))
))
data class ShoppingItem(
    @PrimaryKey val id:Long,
    val name:String,
    val price:Int,
    @ColumnInfo(name = "list_id") val shoppingListId:Long
)