/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tingken.acs.domain.AlarmDevice;
import com.tingken.acs.remote.player.PlayerApi;
import com.tingken.acs.remote.player.pojo.GroupsResult;
import com.tingken.acs.remote.player.pojo.PlayerResult;
import com.tingken.acs.remote.player.pojo.TermIdListResult;
import com.tingken.acs.remote.player.pojo.TermStateListResult;
import com.tingken.acs.remote.player.pojo.GroupsResult.Group;
import com.tingken.acs.remote.player.pojo.TermStateListResult.TermState;
import com.tingken.acs.service.AlarmDeviceRepository;
import com.tingken.acs.service.around.LicenceValidateTool;

@RestController
@RequestMapping("/acs/api/v1/alarmDevices")
public class AlarmDeviceController {
    @Resource
    AlarmDeviceRepository alarmDeviceRepository;
    @Autowired
    PlayerApi playerApi;
    @Autowired
    LicenceValidateTool licenceValidateTool;

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
                        if (licenceValidateTool.isValid(device)) {
                            alarmDeviceRepository.save(device);
                        }
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

    @GetMapping("/groups")
    public ResponseEntity<Map<String, Object>> groups() {
        PlayerResult loginResult = playerApi.login();
        if (loginResult != null && loginResult.getRet() == 0) {
            GroupsResult groupsResult = playerApi.getGroups();
            if (groupsResult != null && groupsResult.getRet() == 0) {
                Map<String, Object> result = new HashMap<String, Object>();
                List<Map<String, Object>> groups = new ArrayList<Map<String, Object>>();
                for (Group group : groupsResult.getGroupList()) {
                    Map<String, Object> groupInfo = new HashMap<String, Object>();
                    groupInfo.put("name", group.getName());
                    groupInfo.put("id", group.getId());
                    groups.add(groupInfo);
                }
                result.put("groups", groups);
                return ResponseEntity.ok(result);
            }
        }
        return ResponseEntity.notFound().build();
    }

}
