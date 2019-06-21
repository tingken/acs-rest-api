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
import com.tingken.acs.service.AlarmNoticeRepository;
import com.tingken.acs.service.AlarmPlanRepository;
import com.tingken.acs.service.AnemoDataRepository;
import com.tingken.acs.service.SystemSettingRepository;

/**
 * The purpose of this class is to schedule a task to clear anemometer
 * data.
 */
@Configuration
@EnableScheduling
public class AnemoClearTask implements SchedulingConfigurer {

    @Resource
    SystemSettingRepository systemSettingRepository;

    @Resource
    AnemoDataRepository anemoDataRepository;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Creates a new instance of <code>AnemoClearTask</code>.
     */
    public AnemoClearTask() {
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
                    String cron = systemSettingRepository.findByConfigName(Constants.ANEMO_DATA_CLEAR_CRON).getValue();
                    // verify configuration
                    if (StringUtils.isEmpty(cron)) {
                        logger.error("The system configuration '" + Constants.ANEMO_DATA_CLEAR_CRON + "' IS EMPTY now");
                        return null;
                    }
                    // return schedule
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                });
    }

    void clearAnemoData() {
        int anemoAlarmUniqueTime = Integer
                .valueOf(systemSettingRepository.findByConfigName(Constants.ANEMO_DATA_MAX_KEEP_SECONDS).getValue());
        Date expiredDate = DateUtils.addSeconds(new Date(), -anemoAlarmUniqueTime);
        logger.debug("AnemoClearTask clear data with expiredDate: " + expiredDate);

        int result = anemoDataRepository.deleteByExpiredDate(expiredDate);
        logger.debug("AnemoClearTask clear data with result: " + result);
    }
}
