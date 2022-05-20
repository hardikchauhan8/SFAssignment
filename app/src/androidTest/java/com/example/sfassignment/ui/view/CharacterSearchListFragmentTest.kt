package com.example.sfassignment.ui.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.sfassignment.R
import com.example.sfassignment.data.model.CharacterDetailResponse
import com.example.sfassignment.ui.adapters.CharacterListAdapter
import com.google.gson.Gson
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharacterSearchListFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_isCharacterSearchListFragmentVisible_onLaunch() {
        onView(withId(R.id.rcv_character_list)).check(matches(isDisplayed()))
    }

    @Test
    fun test_selectItem_isDetailFragmentVisible() {

        val context = InstrumentationRegistry.getInstrumentation().context
        val data = context.assets.open("chatacter.json").bufferedReader().use { it.readText() }
        val characterList =
            Gson().fromJson(data, Array<CharacterDetailResponse>::class.java).toList()

        onView(withId(R.id.rcv_character_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<CharacterListAdapter.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.tvTitle)).check(matches(withText(characterList[0].name)))
    }
}