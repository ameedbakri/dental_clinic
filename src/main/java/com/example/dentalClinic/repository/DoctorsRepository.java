package com.example.dentalClinic.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.dentalClinic.entity.DoctorsEntity;

@Repository
public interface DoctorsRepository extends JpaRepository<DoctorsEntity, Integer>  {
	
	DoctorsEntity findByUsername(String Username);
	boolean existsByUsername(String username);
	boolean existsByNationalId(String national_ID);
	boolean existsByPhoneNumber(String phone_number);
	
	
	@Query(value = "select * from doctors as d where  not exists(select * from reservation as r where r.time=:time and r.date =:date and r.Doctorid=d.id )",nativeQuery = true)
	List<DoctorsEntity> findAvailableDoctor(LocalDate date,LocalTime time);
}
