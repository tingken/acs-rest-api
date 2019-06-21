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

import com.tingken.acs.domain.AnemoData;

@RepositoryRestResource(collectionResourceRel = "alarm_data", path = "anemoData")
public interface AnemoDataRepository extends PagingAndSortingRepository<AnemoData, Long> {
    //    @Query("")
    //    Page<AnemoData> findByDate(@Param("year") String year, @Param("month") String month, @Param("day") String day,
    //            @Param("page") int page, @Param("size") int size);

    @Query("SELECT COALESCE(MAX(d.windSpeed), 0) FROM AnemoData d WHERE d.receivedTime BETWEEN :startDate AND :endDate")
    float findMaxSpeedByDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Transactional
    @Modifying
    @Query("DELETE FROM AnemoData d WHERE d.receivedTime < :expiredDate")
    int deleteByExpiredDate(@Param("expiredDate") Date expiredDate);

    @Query("SELECT d FROM AnemoData d WHERE d.receivedTime < :expiredDate")
    Iterable<AnemoData> findByExpiredDate(@Param("expiredDate") Date expiredDate);
}
