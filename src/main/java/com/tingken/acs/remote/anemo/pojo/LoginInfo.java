/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.remote.anemo.pojo;

/**
 * This class is a pojo to save the data used in login to Anemometer
 * system.
 */
public class LoginInfo {
    private String username;
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
