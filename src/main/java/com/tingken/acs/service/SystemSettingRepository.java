/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.service;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.tingken.acs.domain.SystemSetting;

@RepositoryRestResource(collectionResourceRel = "system_setting", path = "systemSettings")
public interface SystemSettingRepository extends PagingAndSortingRepository<SystemSetting, Long> {
    List<SystemSetting> findByConfigName(@Param("name") String name);
}
