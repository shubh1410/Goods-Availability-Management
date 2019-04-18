package com.example.godam;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class newUserTest {

    public newUser obj;


    @Before
    public void setUp() throws Exception {
        newUser obj=new newUser();
    }

    @Test
    public void isEmailValidTest() {

        newUser obj=new newUser();
        assertTrue(obj.isEmailValid("name@email.com") && obj.isEmailValid("name@email.co.com"));
    }

    @After
    public void tearDown() throws Exception {
        obj=null;
    }


}