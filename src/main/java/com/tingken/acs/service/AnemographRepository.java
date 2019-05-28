/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.service;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.tingken.acs.domain.Anemograph;

@RepositoryRestResource(collectionResourceRel = "anemograph", path = "anemographs")
public interface AnemographRepository extends PagingAndSortingRepository<Anemograph, Long> {
    Anemograph findByName(@Param("name") String name);
}
