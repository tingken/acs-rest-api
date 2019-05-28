/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.remote.player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is to encapsulate the basic result from players system.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerResult {
    /**
     * 0: success; other: failure.
     */
    @JsonProperty("Ret")
    private int ret;
    @JsonProperty("Remark")
    private String remark;

    /**
     * @return Returns the ret.
     */
    public int getRet() {
        return ret;
    }

    /**
     * @param ret The ret to set.
     */
    public void setRet(int ret) {
        this.ret = ret;
    }

    /**
     * @return Returns the remark.
     */
    public String getRemark() {
        return remark;
    }
    
    /**
     * @param remark The remark to set.
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

}
