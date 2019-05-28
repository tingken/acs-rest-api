/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.remote.player;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The purpose of this class is to
 */
public class GroupsResult extends PlayerResult {
    public static class Group {
        @JsonProperty("ID")
        private int id;
        @JsonProperty("Name")
        private String name;

        /**
         * @return Returns the id.
         */
        public int getId() {
            return id;
        }

        /**
         * @return Returns the name.
         */
        public String getName() {
            return name;
        }

        /**
         * @param id The id to set.
         */
        public void setId(int id) {
            this.id = id;
        }

        /**
         * @param name The name to set.
         */
        public void setName(String name) {
            this.name = name;
        }
    }

    @JsonProperty("Groups")
    private ArrayList<Group> groupList = new ArrayList<Group>();

    /**
     * @return Returns the groupList.
     */
    public ArrayList<Group> getGroupList() {
        return groupList;
    }

    /**
     * @param groupList The groupList to set.
     */
    public void setGroupList(ArrayList<Group> groupList) {
        this.groupList = groupList;
    }

}
