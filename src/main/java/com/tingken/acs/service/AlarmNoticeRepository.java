/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.service;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import com.tingken.acs.domain.AlarmNotice;

@RepositoryRestResource(collectionResourceRel = "alarm_notice", path = "alarmNotices")
public interface AlarmNoticeRepository extends PagingAndSortingRepository<AlarmNotice, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM AlarmNotice n WHERE n.noticeTime < :expiredDate")
    int deleteByExpiredDate(@Param("expiredDate") Date expiredDate);
}
