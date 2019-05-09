/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.auth;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * The purpose of this class is ...
 * TODO javadoc for class AuthInterceptor
 */
public class AuthInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Creates a new instance of <code>AuthInterceptor</code>.
     * TODO javadoc for AuthInterceptor constructor.
     */
    public AuthInterceptor() {
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // TODO Auto-generated method stub
        logger.info("into AuthInterceptor:preHandle");
        System.out.println("into AuthInterceptor:preHandle");
        logger.info("Authorization:" + request.getHeader("Authorization"));
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}
