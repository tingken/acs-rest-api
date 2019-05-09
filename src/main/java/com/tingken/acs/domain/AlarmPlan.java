/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * The purpose of this class is ...
 * TODO javadoc for class AlamType
 */
@Entity
public class AlarmPlan {
    public enum Status {
        NORMAL, PENDING, DELETED
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private int threshold;
    private String alarmContent;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<AlarmDevice> alarmDevices;
    private String alarmDeviceTermIdPattern;
    private int volume;
    @Enumerated
    private Status status;
    

    /**
     * Creates a new instance of <code>AlamType</code>.
     * TODO javadoc for AlamType constructor.
     */
    public AlarmPlan() {
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
     * @return Returns the threshold.
     */
    public int getThreshold() {
        return threshold;
    }

    /**
     * @param threshold The threshold to set.
     */
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    /**
     * @return Returns the alarmContent.
     */
    public String getAlarmContent() {
        return alarmContent;
    }

    /**
     * @param alarmContent The alarmContent to set.
     */
    public void setAlarmContent(String alarmContent) {
        this.alarmContent = alarmContent;
    }

    /**
     * @return Returns the alarmDevices.
     */
    public Set<AlarmDevice> getAlarmDevices() {
        return alarmDevices;
    }

    /**
     * @param alarmDevices The alarmDevices to set.
     */
    public void setAlarmDevices(Set<AlarmDevice> alarmDevices) {
        this.alarmDevices = alarmDevices;
    }

    /**
     * @return Returns the devicePattern.
     */
    public String getAlarmDeviceTermIdPattern() {
        return alarmDeviceTermIdPattern;
    }

    /**
     * @param devicePattern The devicePattern to set.
     */
    public void setAlarmDeviceTermIdPattern(String alarmDeviceTermIdPattern) {
        this.alarmDeviceTermIdPattern = alarmDeviceTermIdPattern;
    }

    /**
     * @return Returns the volume.
     */
    public int getVolume() {
        return volume;
    }

    /**
     * @param volume The volume to set.
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     * @return Returns the status.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status The status to set.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

}
