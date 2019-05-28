/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This class is an entity to save information related to system
 * configuration.
 */
@Entity
public class SystemSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String configName;
    private String value;
    private String type;
    private String description;

    /**
     * Creates a new instance of <code>SystemSetting</code>.
     * TODO javadoc for SystemSetting constructor.
     */
    public SystemSetting() {
    }

    /**
     * @return Returns the configName.
     */
    public String getConfigName() {
        return configName;
    }

    /**
     * @param configName The configName to set.
     */
    public void setConfigName(String configName) {
        this.configName = configName;
    }

    /**
     * @return Returns the value.
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value The value to set.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return Returns the type.
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The type to set.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
