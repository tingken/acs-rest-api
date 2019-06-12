/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tingken.acs.domain.AlarmDevice;
import com.tingken.acs.remote.player.PlayerApi;
import com.tingken.acs.remote.player.PlayerResult;
import com.tingken.acs.remote.player.TermIdListResult;
import com.tingken.acs.remote.player.TermStateListResult;
import com.tingken.acs.remote.player.TermStateListResult.TermState;
import com.tingken.acs.service.AlarmDeviceRepository;

@RestController
@RequestMapping("/acs/api/v1/alarmDevices")
public class AlarmDeviceController {
    @Resource
    AlarmDeviceRepository alarmDeviceRepository;
    @Autowired
    PlayerApi playerApi;

    public AlarmDeviceController() {
    }

    @PostMapping("/refresh")
    public void refresh() {
        PlayerResult loginResult = playerApi.login();
        if (loginResult != null && loginResult.getRet() == 0) {
            TermIdListResult termIdListResult = playerApi.getTermIdList();
            if (termIdListResult != null && termIdListResult.getRet() == 0) {
                TermStateListResult termStateListResult = playerApi.getTermStateList(termIdListResult.getTermIds());
                if (termStateListResult != null && termStateListResult.getRet() == 0) {
                    for (TermState state : termStateListResult.getTermStateList()) {
                        AlarmDevice device = new AlarmDevice();
                        device.setName(state.getName());
                        device.setIp(state.getIp());
                        device.setTermId(state.getId());
                        device.setDeviceDesc(state.getName());
                        alarmDeviceRepository.save(device);
                    }
                }
            }
        }
    }

    @DeleteMapping("/clear")
    public void clear() {
        PlayerResult loginResult = playerApi.login();
        if (loginResult != null && loginResult.getRet() == 0) {
            alarmDeviceRepository.deleteAll();
        }
    }

}
