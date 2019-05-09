/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.service;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.tingken.acs.domain.AlarmPlan;

@RepositoryRestResource(collectionResourceRel = "alarm_plan", path = "alarmPlans")
public interface AlarmPlanRepository extends PagingAndSortingRepository<AlarmPlan, Long> {
    List<AlarmPlan> findByName(@Param("name") String name);
}
