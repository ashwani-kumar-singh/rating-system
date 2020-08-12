package com.daytoday.ratingsystem;

/**
 * Created By Ashwani Singh
 *  Dated: 12th Aug 2020
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableMongoAuditing
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.daytoday.ratingsystem"})
@SpringBootApplication
public class RatingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingSystemApplication.class, args);
	}

}
