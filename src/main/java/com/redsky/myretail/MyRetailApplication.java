package com.redsky.myretail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories({ "com.redsky.myretail"})
@ComponentScan("com.redsky.myretail")
public class MyRetailApplication {

	public static void main(String[] args) {
		System.getProperties().put("server.port", 8080);
		SpringApplication.run(MyRetailApplication.class, args);
	}

}
