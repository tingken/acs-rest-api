/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.auth;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The purpose of this class is ...
 * TODO javadoc for class AuthFilter
 */
public class AuthFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Creates a new instance of <code>AuthFilter</code>.
     * TODO javadoc for AuthFilter constructor.
     */
    public AuthFilter() {
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        logger.info("into AuthFilter:doFilter");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            logger.info(name + ":" + request.getHeader(name));
        }
        //        logger.info("Authorization:" + request.getHeader("Authorization"));
        chain.doFilter(req, resp);
    }

}
