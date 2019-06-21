/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.remote.anemo;

import com.tingken.acs.remote.anemo.pojo.AnemoResult;
import com.tingken.acs.remote.anemo.pojo.LoginResult;

/**
 * This interface is to access anemometer-system and get data.
 */
public interface AnemoApi {

    LoginResult login();

    AnemoResult getAnemoData(String token);

}