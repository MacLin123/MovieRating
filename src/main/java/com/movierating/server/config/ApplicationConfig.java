//package com.movierating.server.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.multipart.MultipartResolver;
//import org.springframework.web.multipart.commons.CommonsMultipartResolver;
//
//import javax.servlet.MultipartConfigElement;
//
//@Configuration
//public class ApplicationConfig {
////    @Bean
////    public MultipartResolver multipartResolver() {
////        CommonsMultipartResolver multipartResolver
////                = new CommonsMultipartResolver();
////        multipartResolver.setMaxUploadSize(Config.MAX_FILE_SIZE.getVal());
////        return multipartResolver;
////    }
//@Bean
//public MultipartConfigElement multipartConfigElement() {
//    return new MultipartConfigElement("");
//}
//
//    @Bean
//    public MultipartResolver multipartResolver() {
//        org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
//        multipartResolver.setMaxUploadSize(1000000);
//        return multipartResolver;
//    }
//}
