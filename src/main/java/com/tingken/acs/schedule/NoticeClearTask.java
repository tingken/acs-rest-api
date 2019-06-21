/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.schedule;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.StringUtils;

import com.tingken.acs.domain.Constants;
import com.tingken.acs.domain.SystemSetting;
import com.tingken.acs.service.AlarmNoticeRepository;
import com.tingken.acs.service.AlarmPlanRepository;
import com.tingken.acs.service.AnemoDataRepository;
import com.tingken.acs.service.SystemSettingRepository;

/**
 * The purpose of this class is to schedule a task to clear alarm
 * notices.
 */
@Configuration
@EnableScheduling
public class NoticeClearTask implements SchedulingConfigurer {

    @Resource
    SystemSettingRepository systemSettingRepository;

    @Resource
    AlarmNoticeRepository alarmNoticeRepository;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Creates a new instance of <code>NoticeClearTask</code>.
     */
    public NoticeClearTask() {
    }

    /**
     * Add a scheduled task.
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        taskRegistrar.addTriggerTask(
                // task content
                () -> clearAnemoData(),
                // set task schedule
                triggerContext -> {
                    // get configuration
                    SystemSetting cronConfig = systemSettingRepository.findByConfigName(Constants.ALARM_NOTICE_CLEAR_CRON);
                    // verify configuration
                    if (cronConfig == null || StringUtils.isEmpty(cronConfig.getValue())) {
                        logger.error(
                                "The system configuration '" + Constants.ALARM_NOTICE_CLEAR_CRON + "' IS EMPTY now");
                        return null;
                    }
                    // return schedule
                    return new CronTrigger(cronConfig.getValue()).nextExecutionTime(triggerContext);
                });
    }

    void clearAnemoData() {
        int anemoAlarmUniqueTime = Integer
                .valueOf(systemSettingRepository.findByConfigName(Constants.ALARM_NOTICE_MAX_KEEP_SECONDS).getValue());
        Date expiredDate = DateUtils.addSeconds(new Date(), -anemoAlarmUniqueTime);
        logger.debug("NoticeClearTask clear notice with expiredDate: " + expiredDate);

        int result = alarmNoticeRepository.deleteByExpiredDate(expiredDate);
        logger.debug("NoticeClearTask clear notice with result: " + result);
    }
}
