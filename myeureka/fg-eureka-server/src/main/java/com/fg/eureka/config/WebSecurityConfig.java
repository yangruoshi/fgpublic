package com.fg.eureka.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //保留这里为了访问eureka控制台和/actuator是能做安全控制
        super.configure(http);
        //忽略
        http.csrf().ignoringAntMatchers("/eureka/**");
    }
}