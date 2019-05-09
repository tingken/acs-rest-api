/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * The purpose of this class is ...
 * TODO javadoc for class Authoritie
 */
@Entity(name = "authorities")
public class Authoritie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "username", nullable = false)
    private User user;

    private String authority;

    /**
     * Creates a new instance of <code>Authoritie</code>.
     * TODO javadoc for Authoritie constructor.
     */
    public Authoritie() {
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
     * @return Returns the authority.
     */
    public String getAuthority() {
        return authority;
    }

    /**
     * @param authority The authority to set.
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }

}
