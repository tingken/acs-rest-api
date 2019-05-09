/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.service;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.tingken.acs.domain.AlarmDevice;

@RepositoryRestResource(collectionResourceRel = "alarm_device", path = "alarmDevices")
public interface AlarmDeviceRepository extends PagingAndSortingRepository<AlarmDevice, Long> {
    List<AlarmDevice> findByName(@Param("name") String name);
}
