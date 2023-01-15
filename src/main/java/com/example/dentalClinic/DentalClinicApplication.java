package com.example.dentalClinic;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.dentalClinic.repository.PatientRepository;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableJpaRepositories(basePackageClasses = PatientRepository.class)
public class DentalClinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(DentalClinicApplication.class, args);
	}
	
}
	