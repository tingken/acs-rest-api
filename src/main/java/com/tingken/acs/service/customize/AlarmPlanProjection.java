/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.service.customize;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.tingken.acs.domain.AlarmDevice;
import com.tingken.acs.domain.AlarmPlan;
import com.tingken.acs.domain.Authority;
import com.tingken.acs.domain.User;
import com.tingken.acs.domain.AlarmPlan.Status;

/**
 * The purpose of this class is to customize the content of AlarmPlan.
 */
@Projection(name = "alarmPlanProjection", types = { AlarmPlan.class })
public interface AlarmPlanProjection {
    String getName();

    Float getThreshold();

    String getAlarmContent();

    Set<AlarmDevice> getAlarmDevices();

    Integer getVolume();

    @Value("#{T(com.tingken.acs.service.customize.DataOutputUtils).parseStatus(target.alarmDevices, target.status)}")
    Status getStatus();

    int getPriority();

    int getRepeatTime();

    @Value("#{T(com.tingken.acs.service.customize.DataOutputUtils).constructDeviceNames(target.alarmDevices)}")
    String getDeviceNames();
}
