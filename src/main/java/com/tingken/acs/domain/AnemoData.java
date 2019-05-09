/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * The purpose of this class is ...
 * TODO javadoc for class AnemoData
 */
@Entity
public class AnemoData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private double windSpeed;
    private Date receivedTime;
    @ManyToOne
    private Anemograph anemograph;

    /**
     * Creates a new instance of <code>AnemoData</code>.
     * TODO javadoc for AnemoData constructor.
     */
    public AnemoData() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @return Returns the windSpeed.
     */
    public double getWindSpeed() {
        return windSpeed;
    }

    /**
     * @param windSpeed The windSpeed to set.
     */
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    /**
     * @return Returns the receivedTime.
     */
    public Date getReceivedTime() {
        return receivedTime;
    }

    /**
     * @param receivedTime The receivedTime to set.
     */
    public void setReceivedTime(Date receivedTime) {
        this.receivedTime = receivedTime;
    }

    /**
     * @return Returns the anemograph.
     */
    public Anemograph getAnemograph() {
        return anemograph;
    }

    /**
     * @param anemograph The anemograph to set.
     */
    public void setAnemograph(Anemograph anemograph) {
        this.anemograph = anemograph;
    }

}
