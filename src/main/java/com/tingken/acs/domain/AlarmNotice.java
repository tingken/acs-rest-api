/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * The purpose of this class is ...
 * TODO javadoc for class AlarmNotice
 */
@Entity
public class AlarmNotice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private AlarmPlan plan;
    private String noticeContent;
    private Date noticeTime;
    private double value;
    private double threshold;
    @ElementCollection
    private Set<String> deviceNames;
    @ManyToMany(targetEntity = AlarmNotice.class)
    private Set<AlarmDevice> alarmDevices;

    /**
     * Creates a new instance of <code>AlarmNotice</code>.
     * TODO javadoc for AlarmNotice constructor.
     */
    public AlarmNotice() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @return Returns the plan.
     */
    public AlarmPlan getPlan() {
        return plan;
    }

    /**
     * @param plan The plan to set.
     */
    public void setPlan(AlarmPlan plan) {
        this.plan = plan;
    }

    /**
     * @return Returns the noticeContent.
     */
    public String getNoticeContent() {
        return noticeContent;
    }

    /**
     * @param noticeContent The noticeContent to set.
     */
    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    /**
     * @return Returns the noticeTime.
     */
    public Date getNoticeTime() {
        return noticeTime;
    }

    /**
     * @param noticeTime The noticeTime to set.
     */
    public void setNoticeTime(Date noticeTime) {
        this.noticeTime = noticeTime;
    }

    /**
     * @return Returns the value.
     */
    public double getValue() {
        return value;
    }

    /**
     * @param value The value to set.
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * @return Returns the threshold.
     */
    public double getThreshold() {
        return threshold;
    }

    /**
     * @param threshold The threshold to set.
     */
    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    /**
     * @return Returns the deviceNames.
     */
    public Set<String> getDeviceNames() {
        return deviceNames;
    }

    /**
     * @param deviceNames The deviceNames to set.
     */
    public void setDeviceNames(Set<String> deviceNames) {
        this.deviceNames = deviceNames;
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

}
