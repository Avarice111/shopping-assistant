package forthelulz.shoppingassistant

import android.widget.ListView

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.Matcher
import org.hamcrest.Description
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class AddShoppingListTest {

    @Rule
    public val activity = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun addTest() {
        val testListName1 = "test list 1"
        onView(withId(R.id.addListButton)).perform(click())

        onView(withId(R.id.newListName)).perform(replaceText(testListName1))
        onView(withId(R.id.newListName)).check(matches(withText(testListName1)))


        onView(withId(R.id.addItemButton)).perform(click())
        onView(withId(R.id.viewListView)).check(ViewAssertions.matches(Matchers.withListSize(1)))
    }

}

internal object Matchers {
    fun withListSize(size: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun matchesSafely(view: View): Boolean {
                return (view as ListView).count == size
            }

            override fun describeTo(description: Description) {
                description.appendText("ListView should have $size items")
            }
        }
    }
}