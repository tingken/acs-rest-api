/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.util;

import static org.junit.Assert.*;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * The purpose of this class is ... TODO javadoc for class
 * LicenceEncode
 */
public class LicenceEncode {

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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

    @Test
    public void test() {
        Map<String, String> idIpMap = new HashMap<String, String>();
        idIpMap.put("1", "192.168.0.1");
        idIpMap.put("2", "192.168.2.3");
        String content = "acs warranty:{1:192.168.0.1,2:192.168.2.3}";
        assertTrue(check(encode(idIpMap), "1", "192.168.0.1"));
        assertTrue(check(encode(idIpMap), null, "192.168.2.3"));
        assertFalse(check(content, "1", "192.168.0.1"));
        assertFalse(check(content, "2", "192.168.2.3"));
        assertFalse(check(Base64.getEncoder().encodeToString(content.getBytes()), "1", "192.168.0.1"));
        content = "acs warranty:{1:" + passwordEncoder.encode("192.168.0.1") + ",2:192.168.2.3}";
        assertTrue(check(Base64.getEncoder().encodeToString(content.getBytes()), "1", "192.168.0.1"));
        assertFalse(check(Base64.getEncoder().encodeToString(content.getBytes()), "2", "192.168.2.3"));
    }

    String encode(Map<String, String> idIpMap) {
        StringBuilder builder = new StringBuilder();
        builder.append("acs warranty:{");
        for (String id : idIpMap.keySet()) {
            builder.append(id);
            builder.append(':');
            builder.append(passwordEncoder.encode(idIpMap.get(id)));
            builder.append(',');
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append('}');
        return Base64.getEncoder().encodeToString(builder.toString().getBytes());
    }

    boolean check(String licence, String id, String ip) {
        String info;
        try {
            info = new String(Base64.getDecoder().decode(licence));
        } catch (IllegalArgumentException e) {
            return false;
        }
        if (!info.startsWith("acs warranty:{")) {
            return false;
        }
        if (!info.endsWith("}")) {
            return false;
        }
        String idIpMapContent = info.substring(14, info.length() - 1);
        for (String idIp : idIpMapContent.split(",")) {
            String[] entry = idIp.split(":");
            if (id == null || id.equals(entry[0])) {
                if (passwordEncoder.matches(ip, entry[1])) {
                    return true;
                }
                if (id != null) {
                    return false;
                }
            }
        }
        return false;
    }

    boolean check(String licence, String ip) {
        String info;
        try {
            info = new String(Base64.getDecoder().decode(licence));
        } catch (IllegalArgumentException e) {
            return false;
        }
        if (!info.startsWith("acs warranty:{")) {
            return false;
        }
        if (!info.endsWith("}")) {
            return false;
        }
        String idIpMapContent = info.substring(14, info.length() - 1);
        for (String idIp : idIpMapContent.split(",")) {
            String[] entry = idIp.split(":");
            if (passwordEncoder.matches(ip, entry[1])) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void output() {
        Map<String, String> idIpMap = new HashMap<String, String>();
        idIpMap.put("11", "192.168.0.1");
        idIpMap.put("12", "192.168.2.3");
        System.out.println(encode(idIpMap));
    }

}
