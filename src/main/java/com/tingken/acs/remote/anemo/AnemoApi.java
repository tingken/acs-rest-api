/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.remote.anemo;

/**
 * This interface is to access anemometer-system and get data.
 */
public interface AnemoApi {

    LoginResult login();

    AnemoResult getAnemoData(String token);

}