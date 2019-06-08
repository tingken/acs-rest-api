/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.remote.player;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The purpose of this class is ...
 * TODO javadoc for class TermStateListResult
 */
public class TermStateListResult extends PlayerResult {
    public static class TermState {
        @JsonProperty("ID")
        private int id;
        @JsonProperty("Name")
        private String name;
        @JsonProperty("IP")
        private String ip;
        /**
         * -1: offline, 0: idle, 1: busy.
         */
        @JsonProperty("Status")
        private int status;
        @JsonProperty("WorkStatus")
        private int workStatus;
        @JsonProperty("CallStatus")
        private int callStatus;
        @JsonProperty("Sid")
        private int sId;
        @JsonProperty("Vol")
        private int vol;
        @JsonProperty("Number")
        private int number;

        // Getter Methods 

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getIp() {
            return ip;
        }

        public int getStatus() {
            return status;
        }

        public int getWorkStatus() {
            return workStatus;
        }

        public int getCallStatus() {
            return callStatus;
        }

        public int getSid() {
            return sId;
        }

        public int getVol() {
            return vol;
        }

        public int getNumber() {
            return number;
        }

        // Setter Methods 

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setWorkStatus(int workStatus) {
            this.workStatus = workStatus;
        }

        public void setCallStatus(int callStatus) {
            this.callStatus = callStatus;
        }

        public void setSid(int sId) {
            this.sId = sId;
        }

        public void setVol(int vol) {
            this.vol = vol;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }

    @JsonProperty("Terms")
    ArrayList<TermState> termStateList = new ArrayList<TermState>();

    /**
     * @return Returns the termStateList.
     */
    public ArrayList<TermState> getTermStateList() {
        return termStateList;
    }

    /**
     * @param termStateList The termStateList to set.
     */
    public void setTermStateList(ArrayList<TermState> termStateList) {
        this.termStateList = termStateList;
    }

}
