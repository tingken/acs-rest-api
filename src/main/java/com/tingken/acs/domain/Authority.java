/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * This class is an entity bean for authority.
 */
@Entity(name = "authorities")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "username", nullable = false)
    private User user;

    @Column(name = "authority")
    private String role;

    /**
     * Creates a new instance of <code>Authority</code>.
     */
    public Authority() {
    }

    /**
     * Creates a new instance of <code>Authority</code>.
     */
    public Authority(User user, String role) {
        this.user = user;
        this.role = role;
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
     * @return Returns the authority.
     */
    public String getRole() {
        return role;
    }

    /**
     * @param authority The authority to set.
     */
    public void setRole(String role) {
        this.role = role;
    }

}
