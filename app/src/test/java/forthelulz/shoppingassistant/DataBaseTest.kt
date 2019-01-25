package forthelulz.shoppingassistant

import org.junit.After
import org.junit.Before
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.RuntimeEnvironment
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(RobolectricTestRunner::class)
@Config(
    constants = BuildConfig::class,
    packageName = "forthelulz.shoppingassistant"
    )
class DataBaseTest {

    var db:AppDatabase? = null

    @Before
    fun initDB() {
        db = AppDatabase(RuntimeEnvironment.application)
    }

    @After
    fun teardownDB() {
        db?.clearDB()
    }

    @Test
    fun testSelectAllLists() {
        db?.shoppingListDAO()?.insertAll(ShoppingList(0,"test 1"), ShoppingList(0,"test 2"))
        val got = db?.shoppingListDAO()?.getAll()
        assertEquals("Should have 2 lists" ,got?.size, 2)
        assertArrayEquals(got?.toTypedArray(), arrayOf(ShoppingList(0,"test 1"), ShoppingList(0,"test 2")))

    }

}