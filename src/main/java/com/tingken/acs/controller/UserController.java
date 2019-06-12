/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tingken.acs.domain.User;
import com.tingken.acs.domain.UserLoginInfo;
import com.tingken.acs.service.UserLoginInfoRepository;
import com.tingken.acs.service.UserRepository;
import com.tingken.acs.util.MD5Util;

@RestController
@RequestMapping("/acs/api/v1")
public class UserController {
    @Resource
    UserRepository userRepository;
    @Resource
    UserLoginInfoRepository userLoginInfoRepository;

    public UserController() {
        // TODO Auto-generated constructor stub
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User user) throws Exception {
        List<User> users = userRepository.findByName(user.getName());
        if (users != null && users.size() == 1) {
            if (users.get(0).getPassword().equals(user.getPassword())) {
                UserLoginInfo info = new UserLoginInfo();
                info.setUser(users.get(0));
                info.setToken(MD5Util.string2MD5(users.get(0).getName() + System.currentTimeMillis()));
                userLoginInfoRepository.save(info);
                Map<String, String> result = new HashMap<String, String>();
                result.put("account", user.getName());
                result.put("token", info.getToken());
                return ResponseEntity.status(HttpStatus.CREATED)
                        .header(HttpHeaders.SET_COOKIE, "Authorization=" + "Bearer " + info.getToken()).body(result);
            }
        }
        throw new Exception("");
    }

    @PostMapping
    public Map<String, String> add(String userName, String password) throws Exception {
        List<User> users = userRepository.findByName(userName);
        if (users == null || users.size() == 0) {
            if(users.get(0).getPassword().equals(password)){
                UserLoginInfo info = new UserLoginInfo();
                info.setUser(users.get(0));
                info.setToken(MD5Util.string2MD5(users.get(0).getName() + System.currentTimeMillis()));
                userLoginInfoRepository.save(info);
                Map<String, String> result = new HashMap<String, String>();
                result.put("account", userName);
                result.put("token", info.getToken());
            }
        }
        throw new Exception("");
    }

}
