package com.example.godam;

//import android.support.test.runner.AndroidJUnit4;

import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
//import androidx.test.ext.junit.runners.AndroidJUnit4;



import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private MainActivity mActivity = null;

    @Before
    public void setUp() {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view = mActivity.findViewById(R.id.login);

        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}