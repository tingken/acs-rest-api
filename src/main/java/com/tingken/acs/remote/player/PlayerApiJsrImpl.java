/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.remote.player;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * The purpose of this class is to encapsulate rest API to access
 * players system.
 */
public class PlayerApiJsrImpl implements PlayerApi {
    private static final String REST_URI = "http://192.168.1.6";

    private static String token = null;

    private Client client = ClientBuilder.newClient();

    /* (non-Javadoc)
     * @see com.tingken.acs.remote.player.PlayerApi#login()
     */
    @Override
    public PlayerResult login() {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUsername("admin");
        loginInfo.setPassword("admin");
        Response resp = client.target(REST_URI).path("/login").request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(loginInfo, MediaType.APPLICATION_JSON));
        if (resp.getStatus() >= 200 && resp.getStatus() < 300) {
            if (!resp.getHeaders().containsKey("Content-Type")) {
                resp.getHeaders().add("Content-Type", "application/json");
            }
            PlayerResult result = resp.readEntity(PlayerResult.class);
            if (result.getRet() == 0) {
                token = resp.getCookies().get("JSESSIONID").getValue();
            }
            return result;
        }
        return null;
    }

    /* (non-Javadoc)
     * @see com.tingken.acs.remote.player.PlayerApi#getTermIdList()
     */
    @Override
    public TermIdListResult getTermIdList() {
        Response resp = client.target(REST_URI).path("/getTermIds").request(MediaType.APPLICATION_JSON)
                .cookie("JSESSIONID", token).post(Entity.entity("", MediaType.APPLICATION_JSON));
        if (resp.getStatus() >= 200 && resp.getStatus() < 300) {
            if (!resp.getHeaders().containsKey("Content-Type")) {
                resp.getHeaders().add("Content-Type", "application/json");
            }
            TermIdListResult result = resp.readEntity(TermIdListResult.class);
            return result;
        }
        return null;
    }

    /* (non-Javadoc)
     * @see com.tingken.acs.remote.player.PlayerApi#getTermStateList(java.util.List)
     */
    @Override
    public TermStateListResult getTermStateList(List<Integer> idList) {
        HashMap<String, Object> requestBody = new HashMap<String, Object>();
        requestBody.put("TermIDs", idList);
        Response resp = client.target(REST_URI).path("/getTermState").request(MediaType.APPLICATION_JSON)
                .cookie("JSESSIONID", token).post(Entity.entity(requestBody, MediaType.APPLICATION_JSON));
        if (resp.getStatus() >= 200 && resp.getStatus() < 300) {
            if (!resp.getHeaders().containsKey("Content-Type")) {
                resp.getHeaders().add("Content-Type", "application/json;charset=gbk");
            }
            resp.bufferEntity();
            PlayerResult commonResult = resp.readEntity(PlayerResult.class);
            if (commonResult.getRet() == 0) {
                TermStateListResult result = resp.readEntity(TermStateListResult.class);
                return result;
            }
        }
        return null;
    }

    /* (non-Javadoc)
     * @see com.tingken.acs.remote.player.PlayerApi#getGroups()
     */
    @Override
    public GroupsResult getGroups() {
        Response resp = client.target(REST_URI).path("/getGroups").request(MediaType.APPLICATION_JSON)
                .cookie("JSESSIONID", token).post(Entity.entity("", MediaType.APPLICATION_JSON));
        if (resp.getStatus() >= 200 && resp.getStatus() < 300) {
            if (!resp.getHeaders().containsKey("Content-Type")) {
                resp.getHeaders().add("Content-Type", "application/json;charset=gbk");
            }
            resp.bufferEntity();
            PlayerResult commonResult = resp.readEntity(PlayerResult.class);
            if (commonResult.getRet() == 0) {
                GroupsResult result = resp.readEntity(GroupsResult.class);
                return result;
            }
        }
        return null;
    }

    /* (non-Javadoc)
     * @see com.tingken.acs.remote.player.PlayerApi#playText(com.tingken.acs.remote.player.TextPlayInfo)
     */
    @Override
    public PlayerResult playText(TextPlayInfo requestBody) {
        Response resp = client.target(REST_URI).path("/TextPlay").request(MediaType.APPLICATION_JSON)
                .cookie("JSESSIONID", token).post(Entity.entity(requestBody, MediaType.APPLICATION_JSON));
        if (resp.getStatus() >= 200 && resp.getStatus() < 300) {
            if (!resp.getHeaders().containsKey("Content-Type")) {
                resp.getHeaders().add("Content-Type", "application/json");
            }
            PlayerResult result = resp.readEntity(PlayerResult.class);
            return result;
        }
        return null;
    }

}
