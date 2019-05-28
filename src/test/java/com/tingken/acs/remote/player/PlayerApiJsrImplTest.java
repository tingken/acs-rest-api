/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.remote.player;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The purpose of this class is to test PlayerApiJsrImpl.
 */
public class PlayerApiJsrImplTest {

    PlayerApi api = new PlayerApiJsrImpl();

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
     * Test method for {@link com.tingken.acs.remote.player.PlayerApiJsrImpl#login()}.
     */
    @Test
    public void testLogin() {
        PlayerResult result = api.login();
        assertNotNull(result);
    }

    @Test
    public void testGetTermIdList() throws JsonProcessingException {
        PlayerResult loginResult = api.login();
        assertNotNull(loginResult);
        TermIdListResult result = api.getTermIdList();
        assertNotNull(result);
        System.out.println(new ObjectMapper().writeValueAsString(result));
    }

    @Test
    public void testGetTermStateList() throws JsonProcessingException {
        PlayerResult loginResult = api.login();
        assertNotNull(loginResult);
        TermIdListResult termIdListResult = api.getTermIdList();
        assertNotNull(termIdListResult);
        TermStateListResult result = api.getTermStateList(termIdListResult.getTermIds());
        assertNotNull(result);
        System.out.println(new ObjectMapper().writeValueAsString(result));
    }

    @Test
    public void testGetGroups() throws JsonProcessingException {
        PlayerResult loginResult = api.login();
        assertNotNull(loginResult);
        GroupsResult result = api.getGroups();
        assertNotNull(result);
        System.out.println(new ObjectMapper().writeValueAsString(result));
    }

    @Test
    public void testPlayText() throws JsonProcessingException {
        PlayerResult loginResult = api.login();
        assertNotNull(loginResult);
        TermIdListResult termIdListResult = api.getTermIdList();
        assertNotNull(termIdListResult);
        TextPlayInfo requestBody = new TextPlayInfo();
        requestBody.setContent("雷电预警，请尽速撤离现场");
        requestBody.setTargetIds(termIdListResult.getTermIds());
        requestBody.setTargetType(1);
        PlayerResult result = api.playText(requestBody);
        assertNotNull(result);
        System.out.println(new ObjectMapper().writeValueAsString(result));
    }
}
