/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.remote.player;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TermIdListResult extends PlayerResult {
    @JsonProperty("TermIds")
    private ArrayList<Integer> termIds = new ArrayList<Integer>();

    // Getter Methods 

    /**
     * @return Returns the termIDs.
     */
    public ArrayList<Integer> getTermIds() {
        return termIds;
    }

    // Setter Methods 

    /**
     * @param termIDs The termIDs to set.
     */
    public void setTermIds(ArrayList<Integer> termIds) {
        this.termIds = termIds;
    }
}
