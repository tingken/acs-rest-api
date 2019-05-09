/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.service;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import com.tingken.acs.domain.UserLoginInfo;

@RepositoryRestResource(collectionResourceRel = "user_login_info", path = "UserLoginInfo")
public interface UserLoginInfoRepository extends PagingAndSortingRepository<UserLoginInfo, Long> {
    //    List<UserLoginInfo> findByUserName(@Param("name") String name);
    //
    //    @Modifying
    //    @Query(value = "", nativeQuery = true)
    //    @Transactional
    //    void login(@Param("account") String userName, @Param("pwd") String password);
}
