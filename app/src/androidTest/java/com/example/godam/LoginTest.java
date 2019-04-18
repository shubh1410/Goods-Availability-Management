package com.example.godam;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;


@RunWith(AndroidJUnit4.class)

public class LoginTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    private String username_tobe_typed="a@gmail.com";
    private String correct_password ="aaaaaa";
    private String wrong_password = "aaa";

    @Before
    public void setUp() throws Exception{
        Intents.init();
    }

    @Test
    public void loginTest_success(){

        Espresso.onView((withId(R.id.username)))
                .perform(ViewActions.typeText(username_tobe_typed),closeSoftKeyboard());

        Espresso.onView(withId(R.id.password))
                .perform(ViewActions.typeText(correct_password),closeSoftKeyboard());

        Espresso.onView(withId(R.id.login))
                .perform(ViewActions.click());

        Espresso.onView(withText("Signed in")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

    }

    @After
    public void tearDown() throws Exception{
        Intents.release();
    }
}