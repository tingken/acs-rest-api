/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.remote.player.pojo;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The purpose of this class is to encapsulate the result in getting
 * termId list from the player system.
 */
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
