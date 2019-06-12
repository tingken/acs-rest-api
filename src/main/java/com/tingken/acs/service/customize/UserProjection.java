/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.service.customize;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.tingken.acs.domain.Authority;
import com.tingken.acs.domain.User;

/**
 * The purpose of this class is to customize the content of User.
 */
@Projection(name = "userProjection", types = { User.class })
public interface UserProjection {
    String getName();

    String getNickname();

    String getEmail();

    String getPassword();

    boolean isEnabled();

    String getUserDesc();

    Set<Authority> getAuthorities();
}
