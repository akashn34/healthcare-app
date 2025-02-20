package com.healthcare;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.healthcare.entity.Login;
import com.healthcare.repository.LoginRepository;

import jakarta.annotation.PostConstruct;

@SpringBootApplication(scanBasePackages = "com.healthcare.*")
@EnableDiscoveryClient
@EntityScan(basePackages = "com.healthcare.entity")
@EnableJpaRepositories(basePackages = "com.healthcare.repository")
public class BackendHealthcareAppApplication {
	
	
	@Autowired
	LoginRepository loginRepository;
	
	
	@PostConstruct
	public void init() {
		System.out.println("This Method Called...");
		Login ll = new Login();
		ll.setEmailid("admin@gmail.com");
		ll.setPassword("admin@123");
		ll.setTypeofuser("admin");
		Optional<Login> result = loginRepository.findById(ll.getEmailid());
		if(result.isPresent()) {
			System.err.println("Account already present...");
		}else {
			loginRepository.save(ll);
			System.err.println("Admin account created");
		}
		
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendHealthcareAppApplication.class, args);
		System.err.println("healthcare up!");
	}

}
