/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.schedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.StringUtils;

import com.tingken.acs.domain.AlarmDevice;
import com.tingken.acs.domain.AlarmNotice;
import com.tingken.acs.domain.AlarmPlan;
import com.tingken.acs.domain.Constants;
import com.tingken.acs.remote.player.PlayerApi;
import com.tingken.acs.remote.player.PlayerApiSpringImpl;
import com.tingken.acs.remote.player.pojo.PlayerResult;
import com.tingken.acs.remote.player.pojo.TextPlayInfo;
import com.tingken.acs.service.AlarmNoticeRepository;
import com.tingken.acs.service.AlarmPlanRepository;
import com.tingken.acs.service.AnemoDataRepository;
import com.tingken.acs.service.SystemSettingRepository;

/**
 * The purpose of this class is to schedule a task to check plans and
 * data for making alarm notices.
 */
@Configuration
@EnableScheduling
public class AnemoCheckTask implements SchedulingConfigurer {

    @Resource
    SystemSettingRepository systemSettingRepository;

    @Resource
    AnemoDataRepository anemoDataRepository;

    @Resource
    AlarmNoticeRepository alarmNoticeRepository;

    @Resource
    AlarmPlanRepository alarmPlanRepository;

    @Autowired
    EntityManager em;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Creates a new instance of <code>AnemoCheckTask</code>.
     */
    public AnemoCheckTask() {
    }

    /**
     * 执行定时任务.
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        taskRegistrar.addTriggerTask(
                // task content
                () -> checkAnemoData(),
                // set task schedule
                triggerContext -> {
                    // get configuration
                    String cron = systemSettingRepository.findByConfigName(Constants.ANEMO_ALARM_CHECK_CRON).getValue();
                    // verify configuration
                    if (StringUtils.isEmpty(cron)) {
                        logger.error(
                                "The system configuration '" + Constants.ANEMO_ALARM_CHECK_CRON + "' IS EMPTY now");
                    }
                    // return schedule
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                });
    }

    @Bean
    public PlayerApi playerApi() {
        String uri = systemSettingRepository.findByConfigName(Constants.BROAD_CAST_SERVER_URI).getValue();
        return new PlayerApiSpringImpl(uri);
    }

    void checkAnemoData() {
        int anemoAlarmUniqueTime = Integer
                .valueOf(systemSettingRepository.findByConfigName(Constants.SAME_ANEMO_ALARM_UNIQUE_TIME).getValue());
        Date startDate = DateUtils.addSeconds(new Date(), -anemoAlarmUniqueTime);
        float maxSpeed = anemoDataRepository.findMaxSpeedByDate(startDate, new Date());
        System.out.println("AnemoCheckTask 执行动态定时任务  startDate: " + startDate + ", endDate: " + new Date()
                + ", maxSpeed: " + maxSpeed);

        Query q = em.createQuery(
                "select p from AlarmPlan p where p.threshold < :maxSpeed and p.threshold > (select COALESCE(max(n.threshold), 0) from AlarmNotice n where n.noticeTime > :startDate) order by p.threshold desc");
        q.setParameter("maxSpeed", maxSpeed);
        q.setParameter("startDate", startDate, TemporalType.DATE);
        List<AlarmPlan> planList = q.getResultList();
        for (AlarmPlan plan : planList) {
            System.out.println("AnemoCheckTask name: " + plan.getName() + ", threshold: " + plan.getThreshold());
        }
        if (planList.size() > 0) {
            AlarmPlan plan = planList.get(0);
            Set<AlarmDevice> devices = plan.getAlarmDevices();
            if (devices != null) {
                TextPlayInfo req = new TextPlayInfo();
                req.setContent(plan.getAlarmContent());
                req.setPlaytime(plan.getRepeatTime());
                req.setPlayPrior(plan.getPriority());
                ArrayList<Integer> targetIdList = new ArrayList<Integer>(devices.size());
                Set<String> deviceNames = new HashSet<String>();
                for (AlarmDevice device : devices) {
                    targetIdList.add(device.getTermId());
                    deviceNames.add(device.getName());
                }
                req.setTargetIds(targetIdList);
                // send play request
                playerApi().login();
                PlayerResult res = playerApi().playText(req);
                if (res.getRet() == 0) {
                    // save notice record
                    AlarmNotice notice = new AlarmNotice();
                    notice.setDeviceNames(deviceNames);
                    notice.setNoticeContent(req.getContent());
                    notice.setNoticeTime(new Date());
                    notice.setPlan(plan);
                    notice.setThreshold(plan.getThreshold());
                    notice.setValue(maxSpeed);
                    alarmNoticeRepository.save(notice);
                }
            }
        }
    }

}
