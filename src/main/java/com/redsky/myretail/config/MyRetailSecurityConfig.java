package com.redsky.myretail.config;

import com.redsky.myretail.filter.MyRetailSecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class MyRetailSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1").password("user1Pass")
                .roles("USER");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/securityNone").permitAll()
                .antMatchers("/actuator/health").permitAll()
                .antMatchers("/service-health").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();

        http.addFilterAfter(new MyRetailSecurityFilter(),
                BasicAuthenticationFilter.class);
    }

}
