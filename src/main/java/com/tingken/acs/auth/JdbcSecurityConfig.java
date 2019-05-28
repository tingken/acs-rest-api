/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.auth;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.tingken.acs.auth.security.CustomAccessDeniedHandler;
import com.tingken.acs.auth.security.MySavedRequestAwareAuthenticationSuccessHandler;
import com.tingken.acs.auth.security.RestAuthenticationEntryPoint;

/**
 * The purpose of this class is to configure spring security with JDBC
 * back-end.
 */
@EnableWebSecurity
public class JdbcSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private MySavedRequestAwareAuthenticationSuccessHandler mySuccessHandler;

    private SimpleUrlAuthenticationFailureHandler myFailureHandler = new SimpleUrlAuthenticationFailureHandler();

    /**
     * Creates a new instance of <code>WebSecurityConfig</code>.
     */
    public JdbcSecurityConfig() {
    }

    /**
     * Creates a new instance of <code>WebSecurityConfig</code>.
     */
    public JdbcSecurityConfig(boolean disableDefaults) {
        super(disableDefaults);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder());
//                .withUser("user").password(new BCryptPasswordEncoder().encode("password")).roles("USER").and()
//                .withUser("admin").password(new BCryptPasswordEncoder().encode("password")).roles("ADMIN");
        //        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //        super.configure(http);
        http.cors().and().csrf().disable().authorizeRequests().antMatchers("/systemSettings/**").hasRole("ADMIN")
                .antMatchers("/login*").permitAll()
                .anyRequest().authenticated().and().exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(restAuthenticationEntryPoint).and()
                .formLogin().loginPage("/login.html").loginProcessingUrl("/perform_login")
                .successHandler(mySuccessHandler)
                .failureHandler(myFailureHandler)
                .and().httpBasic();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
