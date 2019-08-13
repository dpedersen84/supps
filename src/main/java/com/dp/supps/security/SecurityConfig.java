/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dp.supps.security;

import com.dp.supps.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author dpede
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private RestAuthenticationSuccessHandler successHandler;
    
    @Autowired
    UserDetailsServiceImpl userDetails;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors()
                .and()
                .authorizeRequests()
//                .antMatchers("/api/products").authenticated()
                .antMatchers("/admin").hasRole("ADMIN")
//                .antMatchers("/api/users").permitAll()
                .antMatchers("/api/**").permitAll()
                .and()
                .formLogin()
                .successHandler(successHandler)
                .and()
                .logout();

        http.csrf().disable();

    }
    
    @Autowired
    public void configureGlobalInDB(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetails).passwordEncoder(bCryptPasswordEncoder());
    }
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
