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
 * TODO javadoc for class Anemograph
 */
@Entity
public class Anemograph {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String label;
    private String AnemoDesc;
    private String sensorUId;

    /**
     * Creates a new instance of <code>Anemograph</code>.
     * TODO javadoc for Anemograph constructor.
     */
    public Anemograph() {
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
     * @return Returns the label.
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label The label to set.
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return Returns the anemoDesc.
     */
    public String getAnemoDesc() {
        return AnemoDesc;
    }

    /**
     * @param anemoDesc The anemoDesc to set.
     */
    public void setAnemoDesc(String anemoDesc) {
        AnemoDesc = anemoDesc;
    }

    /**
     * @return Returns the sensorUId.
     */
    public String getSensorUId() {
        return sensorUId;
    }

    /**
     * @param sensorUId The sensorUId to set.
     */
    public void setSensorUId(String sensorUId) {
        this.sensorUId = sensorUId;
    }

}
