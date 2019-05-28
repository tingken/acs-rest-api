/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.remote.player;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The purpose of this class is ...
 * TODO javadoc for class LoginResult
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
