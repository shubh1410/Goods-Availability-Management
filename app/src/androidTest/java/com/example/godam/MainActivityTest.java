package com.example.godam;

//import android.support.test.runner.AndroidJUnit4;

import android.content.ComponentName;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.InstrumentationRegistry.getTargetContext;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;

//import androidx.test.ext.junit.runners.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

   /* @Rule
    public ActivityTestRule<supervisor> superActivityTestRule = new ActivityTestRule<>(supervisor.class);*/

    @Rule
    public IntentsTestRule<MainActivity> intentsTestRule = new IntentsTestRule<>(MainActivity.class);


    private MainActivity mActivity = null;
    private supervisor sActivity = null;

    private String username_tobe_typed="a@gmail.com";
    private String correct_password ="aaaaaa";
    private String wrong_password = "aaa";

    @Before
    public void setUp() {
       // mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view = mActivity.findViewById(R.id.login);

        assertNotNull(view);
    }



    @Test
    public void loginTest_success(){

        Espresso.onView((withId(R.id.username)))
                .perform(ViewActions.typeText(username_tobe_typed),closeSoftKeyboard());

        Espresso.onView(withId(R.id.password))
                .perform(ViewActions.typeText(correct_password),closeSoftKeyboard());

        Espresso.onView(withId(R.id.login))
                .perform(ViewActions.click(),closeSoftKeyboard());

        //intended(hasComponent(supervisor.class.getName()));
        intended(hasComponent(new ComponentName(getTargetContext(), supervisor.class)));



    }
    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}