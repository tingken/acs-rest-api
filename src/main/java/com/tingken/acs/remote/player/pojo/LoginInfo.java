/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.remote.player.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is a pojo to save the data used in login to the player
 * system.
 */
public class LoginInfo {
    @JsonProperty("User")
    private String username;
    @JsonProperty("Passwd")
    private String password;

    /**
     * @return Returns the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return Returns the password.
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
