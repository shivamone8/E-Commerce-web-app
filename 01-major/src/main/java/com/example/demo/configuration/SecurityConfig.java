 package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

 @Configuration
@EnableWebSecurity
public class SecurityConfig {
     @Autowired
     private  DataSource dataSource;



     @Bean
     public InMemoryUserDetailsManager userDetailsManager(){
         UserDetails Aman = User.builder()
                 .username("aman")
                 .password("{noop}aman123")
                 .roles("USER")
                 .build();

         UserDetails Shiva = User.builder()
                 .username("shiva")
                 .password("{noop}shiva123")
                 .roles("ADMIN")
                 .build();

         UserDetails Ram= User.builder()
                 .username("ram")
                 .password("{noop}ram123")
                 .roles("ADMIN", "USER")
                 .build();
         return new InMemoryUserDetailsManager(Aman, Shiva, Ram);
     }



    @Bean
    public SecurityFilterChain FilterChain(HttpSecurity http) throws Exception {
http.authorizeHttpRequests(configurer->
        configurer
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/shop/**","/category/**", "/viewProduct/**").hasAnyRole( "USER", "ADMIN")
                .requestMatchers("/","/home","/register").permitAll()
                .anyRequest().authenticated()
        )
        .exceptionHandling(configurer ->
                configurer
                        .accessDeniedPage("/AccessDenied"))
        .formLogin( form ->
                form
                        .loginPage("/ShowLoginPage")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll()
        ) .logout(logout ->
                logout
                         .logoutUrl("/logout")
                         .permitAll()
        );
        return http.build();
    }








 }
