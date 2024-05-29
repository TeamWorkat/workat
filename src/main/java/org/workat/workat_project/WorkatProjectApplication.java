package org.workat.workat_project;

import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class WorkatProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkatProjectApplication.class, args);
    }

}
