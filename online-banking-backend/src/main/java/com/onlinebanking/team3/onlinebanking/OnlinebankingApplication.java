package com.onlinebanking.team3.onlinebanking;

import com.onlinebanking.team3.onlinebanking.model.Account;
import com.onlinebanking.team3.onlinebanking.model.User;
import com.onlinebanking.team3.onlinebanking.repository.AccountRepository;
import com.onlinebanking.team3.onlinebanking.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OnlinebankingApplication {

	public  static void main(String[] args) {
		SpringApplication.run(OnlinebankingApplication.class, args);
	}

//	@Bean
//	CommandLineRunner init(UserRepository userRepository, AccountRepository accountRepository){
//		return args -> {
//			User admin = new User();
//			admin.setFirstName("Admin");
//			admin.setLastName("Admin");
//			admin.setEmailId("admin@nexusbank.org");
//			admin.setPhoneNumber("admin@nexusbank.org");
//			admin.setGender("admin");
//			admin.setKyc(true);
//			admin.setAadharNumber("111122223333");
//			admin.setPanNumber("AABBC1234D");
//			admin.setOccupation("admin");
//
//			if(userRepository.findByPhoneNumber("admin@nexusbank.org").isEmpty())
//				userRepository.save(admin);
//
//
//			Account adminAccount = new Account();
//			adminAccount.setIsActive(true);
//			adminAccount.setAccountNo(1001001001);
//			adminAccount.setBalance(0);
//			adminAccount.setUser(admin);
//			adminAccount.setIfscCode("NX1845");
//			accountRepository.save(adminAccount);
//		};
//	}
}
