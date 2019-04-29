package com.xfy.apiall.api;

import com.xfy.apiall.core.config.JwtConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.cache.annotation.EnableCaching;

//@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.xfy.apiall.db",  "com.xfy.apiall.api", "com.xfy.apiall.core"})
@MapperScan("com.xfy.apiall.db.dao")
@EnableTransactionManagement
@EnableScheduling
@EnableCaching

public class Application {

    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtConfiguration());
        registrationBean.addUrlPatterns("/api/*");
        return registrationBean;
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
