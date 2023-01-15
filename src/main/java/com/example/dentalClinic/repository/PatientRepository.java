package com.example.dentalClinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.dentalClinic.entity.PatientsEntity;

@Repository
public interface PatientRepository extends JpaRepository<PatientsEntity, Integer>{
	PatientsEntity findByUsername(String Username);
	boolean existsByPassword(String Password);
	boolean existsByUsername(String username);
	boolean existsByPhoneNumber(String phone_number);
	
	
}
