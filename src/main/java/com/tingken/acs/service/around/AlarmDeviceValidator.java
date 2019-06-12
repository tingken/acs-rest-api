/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.service.around;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tingken.acs.domain.AlarmDevice;

/**
 * The purpose of this class is to validate alarmDevice in the
 * requests.
 */
public class AlarmDeviceValidator implements Validator {
    @Autowired
    LicenceValidateTool licenceValidateTool;

    /**
     * Creates a new instance of <code>AlarmPlanValidator</code>.
     */
    public AlarmDeviceValidator() {
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return AlarmDevice.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AlarmDevice device = (AlarmDevice) target;
        if (!licenceValidateTool.isValid(device)) {
            errors.reject("licence", "device.not-allowed");
        }
    }

}
