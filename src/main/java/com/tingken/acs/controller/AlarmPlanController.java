/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tingken.acs.domain.AlarmDevice;
import com.tingken.acs.domain.AlarmNotice;
import com.tingken.acs.domain.AlarmPlan;
import com.tingken.acs.remote.player.PlayerApi;
import com.tingken.acs.remote.player.pojo.PlayerResult;
import com.tingken.acs.remote.player.pojo.TermStateListResult;
import com.tingken.acs.remote.player.pojo.TextPlayInfo;
import com.tingken.acs.remote.player.pojo.TermStateListResult.TermState;
import com.tingken.acs.service.AlarmNoticeRepository;
import com.tingken.acs.service.AlarmPlanRepository;

@RestController
@RequestMapping("/acs/api/v1/alarmPlans")
public class AlarmPlanController {

    @Resource
    AlarmPlanRepository alarmPlanRepository;
    @Autowired
    PlayerApi playerApi;

    @Resource
    AlarmNoticeRepository alarmNoticeRepository;

    public AlarmPlanController() {
    }

    @PostMapping("/{id}/play")
    public ResponseEntity<Map<String, Object>> play(@PathVariable("id") long id, Principal principal) {
        Map<String, Object> result = new HashMap<String, Object>();
        AlarmPlan plan = alarmPlanRepository.findById(id).get();
        if (plan.getThreshold() != null) {
            result.put("error", -1);
            return ResponseEntity.badRequest().body(result);
        }
        Set<AlarmDevice> alarmDevices = plan.getAlarmDevices();
        PlayerResult loginResult = playerApi.login();
        if (loginResult == null) {
            result.put("error", -2);
            return ResponseEntity.ok(result);
        }
        if (loginResult.getRet() == 0) {
            ArrayList<Integer> termIdList = new ArrayList<Integer>(alarmDevices.size());
            Set<String> deviceNames = new HashSet<String>();
            for (AlarmDevice device : alarmDevices) {
                termIdList.add(device.getTermId());
                deviceNames.add(device.getName());
            }
            TextPlayInfo requestBody = new TextPlayInfo();
            requestBody.setContent(plan.getAlarmContent());
            requestBody.setTargetIds(termIdList);
            requestBody.setTargetType(1);
            PlayerResult playerResult = playerApi.playText(requestBody);
            if (playerResult.getRet() == 0) {
                // save notice record
                AlarmNotice notice = new AlarmNotice();
                notice.setDeviceNames(deviceNames);
                notice.setNoticeContent(plan.getAlarmContent());
                notice.setNoticeTime(new Date());
                notice.setPlan(plan);
                notice.setSenderName(principal.getName());
                alarmNoticeRepository.save(notice);
            }
            result.put("error", playerResult.getRet());
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }
        result.put("error", loginResult.getRet());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<Map<String, Object>> status(@PathVariable("id") long id) {
        Map<String, Object> result = new HashMap<String, Object>();
        AlarmPlan plan = alarmPlanRepository.findById(id).get();
        Set<AlarmDevice> alarmDevices = plan.getAlarmDevices();
        PlayerResult loginResult = playerApi.login();
        if (loginResult != null && loginResult.getRet() == 0) {
            List<Integer> termIdList = new ArrayList<Integer>();
            for (AlarmDevice alarmDevice : alarmDevices) {
                termIdList.add(alarmDevice.getTermId());
            }
            TermStateListResult termStateList = playerApi.getTermStateList(termIdList);
            for (TermState state : termStateList.getTermStateList()) {
                if (state.getStatus() == 0) {
                    result.put("status", 0);
                    return ResponseEntity.ok(result);
                }
            }
        }
        result.put("status", 1);
        return ResponseEntity.ok(result);
    }

}
