package com.onlinebanking.team3.onlinebanking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class OnlinebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlinebankingApplication.class, args);
	}
}
