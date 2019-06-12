/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.remote.anemo;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

/**
 * The purpose of this class is to encapsulate rest API to get
 * anemometer data.
 */
public class AnemoApiJsrImpl implements AnemoApi {
    private String restUri = "http://localhost:8090";

    private Client client = ClientBuilder.newClient();

    public AnemoApiJsrImpl(String restUri) {
        this.restUri = restUri;
    }

    /* (non-Javadoc)
     * @see com.tingken.acs.remote.anemo.AnemoApi#login()
     */
    @Override
    public LoginResult login(){
        LoginResult result = new LoginResult();
        if (StringUtils.isNotBlank(restUri)) {
            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setUsername("admin");
            loginInfo.setPassword("E10ADC3949BA59ABBE56E057F20F883E");//123456 as MD5 format
            Response resp = client.target(restUri).path("/user/login").request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(loginInfo, MediaType.APPLICATION_JSON));
            if (resp.getStatus() >= 200 && resp.getStatus() < 300) {
                result = resp.readEntity(LoginResult.class);
            }
        }
        return result;
    }

    /* (non-Javadoc)
     * @see com.tingken.acs.remote.anemo.AnemoApi#getAnemoData(java.lang.String)
     */
    @Override
    public AnemoResult getAnemoData(String token) {
        if (StringUtils.isNotBlank(restUri)) {
            return client.target(restUri).path("/sensor/selectByCompanyName").queryParam("companyName", "DF")
                    .request(MediaType.APPLICATION_JSON).header("token", token).get(AnemoResult.class);
        }
        return new AnemoResult();
    }

}
