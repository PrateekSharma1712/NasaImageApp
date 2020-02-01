package com.example.nasaapp.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.nasaapp.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ImageListActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<ImageListActivity> =
        ActivityTestRule(ImageListActivity::class.java)

    @Test
    fun allViewsDisplayed() {
        onView(withId(R.id.imagesRecyclerView)).check(matches(isDisplayed()))

        val recyclerView: RecyclerView = activityRule.activity.findViewById(R.id.imagesRecyclerView)

        assert(recyclerView.adapter?.itemCount!! > 0)
        /*if (recyclerView.adapter?.itemCount!! > 0) {
            onView(withId(R.id.imagesRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()))
        }*/
    }
}