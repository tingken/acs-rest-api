/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.service;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.tingken.acs.domain.AlarmNotice;

@RepositoryRestResource(collectionResourceRel = "alarm_notice", path = "alarmNotices")
public interface AlarmNoticeRepository extends PagingAndSortingRepository<AlarmNotice, Long> {
    //    List<AlarmNotice> findByName(@Param("name") String name);
}
