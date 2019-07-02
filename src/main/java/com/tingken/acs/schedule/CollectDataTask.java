/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.schedule;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.tingken.acs.domain.AnemoData;
import com.tingken.acs.domain.Anemograph;
import com.tingken.acs.domain.Constants;
import com.tingken.acs.remote.anemo.AnemoApi;
import com.tingken.acs.remote.anemo.AnemoApiJsrImpl;
import com.tingken.acs.remote.anemo.pojo.AnemoResult;
import com.tingken.acs.remote.anemo.pojo.AnemoResult.AnemoDataItem;
import com.tingken.acs.service.AnemoDataRepository;
import com.tingken.acs.service.AnemographRepository;
import com.tingken.acs.service.SystemSettingRepository;

/**
 * The purpose of this class is to schedule a task to collecting data
 * from anemometer system.
 */
@Configuration
@EnableScheduling
public class CollectDataTask {

    @Resource
    AnemoDataRepository anemoDataRepository;

    @Resource
    AnemographRepository anemographRepository;

    @Resource
    SystemSettingRepository systemSettingRepository;

    /**
     * Creates a new instance of <code>CollectDataTask</code>.
     */
    public CollectDataTask() {
    }

    // add a scheduled task with a fix time
    @Scheduled(fixedRate = 60000)
    private void configureTasks() {
        AnemoResult result = anemoApiJsrImpl().getAnemoData(anemoApiJsrImpl().login().getData().getToken());
        AnemoDataItem[] dataArray = result.getData().getArray();
        for (AnemoDataItem dataItem : dataArray) {
            Anemograph anemograph = anemographRepository.findByName(dataItem.getName());
            if (anemograph == null) {
                anemograph = new Anemograph();
                anemograph.setName(dataItem.getName());
                anemograph.setLabel(dataItem.getLabel());
                anemograph.setSensorUId(dataItem.getSensorUId());
                anemograph.setAnemoDesc(dataItem.getLabel());
                anemographRepository.save(anemograph);
            }
            AnemoData data = new AnemoData();
            data.setReceivedTime(new Date());
            data.setAnemograph(anemograph);
            data.setWindSpeed(dataItem.getWindSpeed());
            anemoDataRepository.save(data);
        }
    }

    @Bean
    public AnemoApi anemoApiJsrImpl() {
        String uri = "";
        SystemSetting uriConfig = systemSettingRepository.findByConfigName(Constants.ANEMO_DATA_SERVER_URI);
        if (uriConfig != null) {
            uri = uriConfig.getValue();
        }
        return new AnemoApiJsrImpl(uri);
    }
}
