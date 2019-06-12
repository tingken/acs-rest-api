/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.tingken.acs.domain.AlarmPlan;

@RepositoryRestResource(collectionResourceRel = "alarm_plan", path = "alarmPlans")
public interface AlarmPlanRepository extends PagingAndSortingRepository<AlarmPlan, Long> {
    List<AlarmPlan> findByName(@Param("name") String name);

    @Query("select p from AlarmPlan p where p.threshold = null")
    Page<AlarmPlan> findAllOtherType(Pageable pageable);

    Optional<AlarmPlan> findByNameAndThreshold(@Param("name") String name, @Param("threshold") String threshold);
}
