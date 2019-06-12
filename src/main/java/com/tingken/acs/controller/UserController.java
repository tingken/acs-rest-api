/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tingken.acs.domain.Authority;
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
    @Autowired
    PasswordEncoder passwordEncoder;

    public UserController() {
        // TODO Auto-generated constructor stub
    }

    @PostMapping("/user/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User user) throws Exception {
        Optional<User> userOptional = userRepository.findById(user.getName());
        if (userOptional != null && userOptional.isPresent()) {
            User userEntity = userOptional.get();
            if (passwordEncoder.matches(user.getPassword(), userEntity.getPassword())) {
                UserLoginInfo info = new UserLoginInfo();
                info.setUser(userEntity);
                info.setToken(MD5Util.string2MD5(userEntity.getName() + System.currentTimeMillis()));
                userLoginInfoRepository.save(info);
                Map<String, String> result = new HashMap<String, String>();
                result.put("account", user.getName());
                result.put("token", info.getToken());
                return ResponseEntity.status(HttpStatus.CREATED)
                        .header(HttpHeaders.SET_COOKIE, "Authorization=" + "Bearer " + info.getToken()).body(result);
            }
            throw new Exception("password can not match");
        }
        throw new Exception("can not find user");
    }

    @PostMapping("/user/changePassword")
    public ResponseEntity<Map<String, String>> changePassword(Authentication authentication,
            @RequestParam("newPwd") String newPwd, @RequestParam("oldPwd") String oldPwd,
            @RequestParam(name = "name", required = false) String name) throws Exception {
        String userName = name;
        if (name != null) {
            if (!isAuthorityOwned(name, authentication)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } else {
            userName = authentication.getName();
        }
        Optional<User> userOptional = userRepository.findById(userName);
        if (userOptional != null && userOptional.isPresent()) {
            User userEntity = userOptional.get();
            if (passwordEncoder.matches(oldPwd, userEntity.getPassword())) {
                userEntity.setPassword(passwordEncoder.encode(newPwd));
                userRepository.save(userEntity);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
            throw new Exception("password can not match");
        }
        throw new Exception("can not find user");
    }

    private boolean isAuthorityOwned(String userName, Authentication authentication) {
        if (!userName.equals(authentication.getName())) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if ("ADMIN".equals(authority.getAuthority())) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    @PostMapping("/users")
    public ResponseEntity<String> add(@RequestParam("name") String userName,
            @RequestParam(name = "desc", required = false) String userDesc, @RequestParam("password") String password,
            @RequestParam("role") String role) throws Exception {
        List<User> users = userRepository.findByName(userName);
        if (users == null || users.size() == 0) {
            User user = new User();
            user.setName(userName);
            user.setNickname(userName);
            if (StringUtils.isNotEmpty(userDesc)) {
                user.setUserDesc(userDesc);
            } else {
                user.setUserDesc(userName);
            }
            user.setPassword(passwordEncoder.encode(password));
            user.setEnabled(true);
            Authority authority = new Authority(user, role);
            Set<Authority> authorities = new HashSet<Authority>();
            authorities.add(authority);
            user.setAuthorities(authorities);
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        throw new Exception("The name has been registered.");
    }

}
