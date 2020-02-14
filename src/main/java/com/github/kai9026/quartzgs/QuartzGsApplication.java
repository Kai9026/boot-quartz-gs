package com.github.kai9026.quartzgs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class QuartzGsApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuartzGsApplication.class, args);
	}

}
