/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * This class is an entity bean to save information related to login
 * information of users.
 */
@Entity
public class UserLoginInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User user;

    private String token;
    private Date logDate;
    private boolean outing;

    /**
     * Creates a new instance of <code>UserLoginInfo</code>.
     * TODO javadoc for UserLoginInfo constructor.
     */
    public UserLoginInfo() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @return Returns the user.
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user The user to set.
     */
    public void setUser(User user) {
        this.user = user;
    }

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

    /**
     * @return Returns the logDate.
     */
    public Date getLogDate() {
        return logDate;
    }

    /**
     * @param logDate The logDate to set.
     */
    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    /**
     * @return Returns the out.
     */
    public boolean isOuting() {
        return outing;
    }

    /**
     * @param out The out to set.
     */
    public void setOuting(boolean outing) {
        this.outing = outing;
    }

}
