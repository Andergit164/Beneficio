///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.cafetito.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
///**
// *
// * @author Anderson
// */
//@Configuration
//public class WebSecurityConfig {
//    
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager)throws Exception{
//        return http
//                .csrf().disable() //deshabalitar el cross site request forgeri
//                .authorizeHttpRequests() //reglas soliciudes
//                .anyRequest() //cualquier solicitud tiene que estar autenticada
//                .authenticated() 
//                .and()
//                .httpBasic() // autenticación básica para que pueda recibir un usuario y una contraseña
//                .and()
//                .sessionManagement() //gestion de las sesiones
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //STATELESS sin estado
//                .and()
//                .build();
//        
//    }
//    
//    @Bean
//    UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("admin")
//            .password(passwordEncoder().encode("admin"))
//            .roles()
//            .build());
//        return manager;
//    }
//    
//    @Bean
//    AuthenticationManager authManger(HttpSecurity http) throws Exception{
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(userDetailsService())
//                .passwordEncoder(passwordEncoder())
//                .and()
//                .build();
//    }
//    
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//    
//}
