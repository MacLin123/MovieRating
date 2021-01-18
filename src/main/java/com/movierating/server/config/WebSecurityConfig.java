//package com.movierating.server.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.header.writers.StaticHeadersWriter;
//import org.springframework.security.web.header.writers.frameoptions.StaticAllowFromStrategy;
//import org.springframework.security.web.header.writers.frameoptions.WhiteListedAllowFromStrategy;
//import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
//
//import java.net.URI;
//import java.util.Arrays;
//
//@EnableWebSecurity
//public class WebSecurityConfig extends
//        WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        http.headers().frameOptions().sameOrigin();
//        http.headers().addHeaderWriter(new XFrameOptionsHeaderWriter(
//                new WhiteListedAllowFromStrategy(
//                        Arrays.asList("http://my-site.com:8888","http://my-site.com:8080"))));
//    }
//}