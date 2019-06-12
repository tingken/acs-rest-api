/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.service.customize;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.tingken.acs.domain.UserLoginInfo;

/**
 * The purpose of this class is to customize the content of
 * UserLoginInfo.
 */
@Projection(name = "loginInfoProjection", types = { UserLoginInfo.class })
public interface LoginInfoProjection {
    Date getLogDate();

    @Value("#{target.user.name}")
    String getUsername();
}
