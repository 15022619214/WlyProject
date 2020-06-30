package com.jysc.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@Configuration
public class SystemApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SystemApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }
    @Bean
    public OpenEntityManagerInViewFilter openEntityManagerInViewFilter(){
    	return new OpenEntityManagerInViewFilter();
    }
}
