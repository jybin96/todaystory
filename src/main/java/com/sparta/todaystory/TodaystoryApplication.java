package com.sparta.todaystory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TodaystoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodaystoryApplication.class, args);
    }

}
