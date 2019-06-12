/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.service.around;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.tingken.acs.domain.AlarmDevice;

/**
 * The purpose of this class is to validate license.
 */
@Component
public class LicenceValidateTool {

    @Value("${acs.licence}")
    String licence;
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * Creates a new instance of <code>LicenceValidateTool</code>.
     */
    public LicenceValidateTool() {
    }

    public boolean isValid(AlarmDevice alarmDevice) {
        System.out.println("acs.licence: " + licence);
        if (check(alarmDevice.getTermId(), alarmDevice.getIp())) {
            return true;
        }
        return false;
    }

    private boolean check(int id, String ip) {
        System.out.println("id: " + id + ", ip: " + ip);
        String info;
        try {
            info = new String(Base64.getDecoder().decode(licence));
        } catch (IllegalArgumentException e) {
            return false;
        }
        if (!info.startsWith("acs warranty:{")) {
            return false;
        }
        if (!info.endsWith("}")) {
            return false;
        }
        String idIpMapContent = info.substring(14, info.length() - 1);
        for (String idIp : idIpMapContent.split(",")) {
            String[] entry = idIp.split(":");
            if (String.valueOf(id).equals(entry[0])) {
                if (passwordEncoder.matches(ip, entry[1])) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }
}
