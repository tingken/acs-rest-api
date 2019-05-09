/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The purpose of this class is ...
 * TODO javadoc for class AlarmDevice
 */
@Entity
public class AlarmDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private int termId;
    private String status;
    private String ip;
    private String deviceDesc;

    /**
     * Creates a new instance of <code>AlarmDevice</code>.
     * TODO javadoc for AlarmDevice constructor.
     */
    public AlarmDevice() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Returns the termId.
     */
    public int getTermId() {
        return termId;
    }

    /**
     * @param termId The termId to set.
     */
    public void setTermId(int termId) {
        this.termId = termId;
    }

    /**
     * @return Returns the status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return Returns the ip.
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip The ip to set.
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return Returns the deviceDesc.
     */
    public String getDeviceDesc() {
        return deviceDesc;
    }

    /**
     * @param deviceDesc The deviceDesc to set.
     */
    public void setDeviceDesc(String deviceDesc) {
        this.deviceDesc = deviceDesc;
    }

}
