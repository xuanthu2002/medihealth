package com.mad.medihealth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MedihealthApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedihealthApplication.class, args);
    }

}
 