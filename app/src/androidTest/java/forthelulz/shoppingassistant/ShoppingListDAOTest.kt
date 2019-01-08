package forthelulz.shoppingassistant

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.Before
import org.junit.After
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ShoppingListDAOTest {

    private var db: AppDatabase? = null;
    private var shoppingListDAO: ShoppingListDAO? = null
    private var shoppingItemDAO: ShoppingItemDAO? = null

    @Before
    fun setup() {
        AppDatabase.TEST_DB = true
        db = AppDatabase.getInstance(InstrumentationRegistry.getTargetContext())
        shoppingListDAO = db?.shoppingListDAO()
        shoppingItemDAO = db?.shoppingItemDAO()
    }



    @After
    fun tearDown() {
        AppDatabase.destroyInstance()
    }



    @Test
    fun insertAndSelectAllShoppingLists() {

    }

}