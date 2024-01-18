package es.salle.pr2

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers
import org.hamcrest.Matchers.isEmptyString
import org.junit.After

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    val email = "estoesun@email.es"
    val passMatch = "passMatch"
    val passBad1 = "passNotMatch"
    val passBad2 = "passNotMatchhhh"

    @Before
    fun setUp(){
        ActivityScenario.launch(MainActivity::class.java)

    }

    /*
    @get:Rule
    var mActivityRule: IntentsTestRule<MainActivity>
    */

    @Test
    fun emailEmptyTest() {
        Espresso.onView(withId(R.id.buttonRegister)).perform(click())
        Espresso.onView(withId(R.id.textViewInstructions)).check(matches(isDisplayed()))
    }

    @Test
    fun emptyPasswordTest(){
        Espresso.onView(withId(R.id.editTextEmail)).perform(typeText(email))
        Espresso.closeSoftKeyboard()
        Espresso.onView(withId(R.id.buttonRegister)).perform(click())
        Espresso.onView(withId(R.id.textViewInstructions)).check(matches(isDisplayed()))
    }

    @Test
    fun passwordNotMatchesTest(){
        Espresso.onView(withId(R.id.editTextEmail)).perform(typeText(email))
        Espresso.onView(withId(R.id.editTextPassword)).perform(typeText(passBad1))
        Espresso.closeSoftKeyboard() //Soy consciente que esto no hace falta, pero y si el usuario usara modo landscape? daría error
        Espresso.onView(withId(R.id.editTextPasswordConfirmation)).perform(typeText(passBad2))
        Espresso.closeSoftKeyboard()
        Espresso.onView(withId(R.id.buttonRegister)).perform(click())
        Espresso.onView(withId(R.id.textViewInstructions)).check(matches(isDisplayed()))
    }

    @Test
    fun allFieldsRightTest(){
        Espresso.onView(withId(R.id.editTextEmail)).perform(typeText(email))
        Espresso.onView(withId(R.id.editTextPassword)).perform(typeText(passMatch))
        Espresso.closeSoftKeyboard()
        Espresso.onView(withId(R.id.editTextPasswordConfirmation)).perform(typeText(passMatch))
        Espresso.closeSoftKeyboard()
        Espresso.onView(withId(R.id.buttonRegister)).perform(click())
        Espresso.onView(withId(R.id.textViewInstructions)).check(matches(isDisplayed()))
    }

    /*
    @Test
    fun intentWebTest(){
        val resultData = Intent()
        val webAddress = "https://lasallefp.com/contactar/"
        resultData.putExtra("web", webAddress)
        val result = Instrumentation.ActivityResult(Activity.RESULT_OK, resultData)

        Espresso.onView(withId(R.id.textViewHelp)).perform(click())
    }
    */

    @Test
    fun intentShowMail(){
        allFieldsRightTest()
        Espresso.onView(withId(R.id.textViewEmail)).check(matches(withText(email)))
    }
}