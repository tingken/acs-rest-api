/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.auth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * The purpose of this class is ...
 * TODO javadoc for class WebSecurityConfig
 */
@EnableWebSecurity
public class JdbcSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    /**
     * Creates a new instance of <code>WebSecurityConfig</code>.
     * TODO javadoc for WebSecurityConfig constructor.
     */
    public JdbcSecurityConfig() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Creates a new instance of <code>WebSecurityConfig</code>.
     * TODO javadoc for WebSecurityConfig constructor.
     */
    public JdbcSecurityConfig(boolean disableDefaults) {
        super(disableDefaults);
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder()).withUser("user")
                .password(new BCryptPasswordEncoder().encode("password")).roles("USER");
//        super.configure(auth);
    }

    /* (non-Javadoc)
     * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO Auto-generated method stub
        super.configure(http);
    }

}
