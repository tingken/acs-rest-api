/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.service.customize;

import java.util.Set;

import com.tingken.acs.domain.AlarmDevice;
import com.tingken.acs.domain.AlarmPlan.Status;

/**
 * This class is a utility of Data output.
 */
public class DataOutputUtils {
    public static String constructDeviceNames(Set<AlarmDevice> targetLists) {
        StringBuilder builder = new StringBuilder();
        for (AlarmDevice device : targetLists) {
            builder.append(device.getName());
            builder.append(",");
        }
        if (builder.length() > 0) {
            return builder.substring(0, builder.length() - 1);
        }
        return "";
    }

    public static Status parseStatus(Set<AlarmDevice> targetLists, Status setStatus) {
        if (targetLists == null || targetLists.size() == 0) {
            return Status.DISABLED;
        }
        return setStatus;
    }

}
