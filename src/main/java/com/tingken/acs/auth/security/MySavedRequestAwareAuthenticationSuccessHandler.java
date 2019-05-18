package com.tingken.acs.auth.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tingken.acs.domain.Authority;
import com.tingken.acs.domain.User;
import com.tingken.acs.domain.UserLoginInfo;
import com.tingken.acs.service.UserLoginInfoRepository;
import com.tingken.acs.service.UserRepository;
import com.tingken.acs.util.MD5Util;

@Component
public class MySavedRequestAwareAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();

    @Resource
    UserRepository userRepository;
    @Resource
    UserLoginInfoRepository userLoginInfoRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
            final Authentication authentication) throws ServletException, IOException {
        final SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UserLoginInfo info = new UserLoginInfo();
            User user = userRepository.findById(authentication.getName()).get();
            info.setUser(user);
            info.setToken(MD5Util.string2MD5(user.getName() + System.currentTimeMillis()));
            userLoginInfoRepository.save(info);
            response.setStatus(HttpServletResponse.SC_CREATED);

            Map<String, Object> body = new HashMap<String, Object>();
            body.put("username", user.getName());
            List<String> roles = new ArrayList<String>();
            for (Authority authority : user.getAuthorities()) {
                roles.add(authority.getAuthority());
            }
            System.out.println("roles:" + roles);
            body.put("roles", roles);

            String json = objectMapper.writeValueAsString(body);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
            response.getWriter().write(json);
        }

        if (savedRequest == null) {
            clearAuthenticationAttributes(request);
            return;
        }
        final String targetUrlParameter = getTargetUrlParameter();
        if (isAlwaysUseDefaultTargetUrl()
                || (targetUrlParameter != null && StringUtils.hasText(request.getParameter(targetUrlParameter)))) {
            requestCache.removeRequest(request, response);
            clearAuthenticationAttributes(request);
            return;
        }

        clearAuthenticationAttributes(request);
    }

    public void setRequestCache(final RequestCache requestCache) {
        this.requestCache = requestCache;
    }
}
