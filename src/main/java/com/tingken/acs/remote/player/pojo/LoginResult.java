/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.remote.player.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The purpose of this class is to encapsulate the result in logging
 * to the player system.
 */
public class LoginResult extends PlayerResult {
    @JsonProperty("JSessionID")
    private String token;

    /**
     * @return Returns the token.
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token The token to set.
     */
    public void setToken(String token) {
        this.token = token;
    }

}
