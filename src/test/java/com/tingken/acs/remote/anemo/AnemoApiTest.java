/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.remote.anemo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tingken.acs.remote.anemo.pojo.AnemoResult;
import com.tingken.acs.remote.anemo.pojo.LoginResult;

/**
 * The purpose of this class is to test AnemoApi.
 */
public class AnemoApiTest {

    AnemoApi api = new AnemoApiJsrImpl("http://localhost:8090");

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for
     * {@link com.tingken.acs.remote.anemo.AnemoApiJsrImpl#login()}.
     * 
     * @throws JsonProcessingException
     */
    @Test
    public void testLogin() throws JsonProcessingException {
        LoginResult result = api.login();
        assertNotNull(result);
        System.out.println(new ObjectMapper().writeValueAsString(result));
    }

    @Test
    public void testGetData() throws JsonProcessingException {
        LoginResult loginResult = api.login();
        assertNotNull(loginResult);
        AnemoResult result = api.getAnemoData(loginResult.getData().getToken());
        assertNotNull(result);
        System.out.println(new ObjectMapper().writeValueAsString(result));
    }
}
