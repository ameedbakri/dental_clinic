package com.example.dentalClinic.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.dentalClinic.entity.ReservationEntity;


@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Integer> {
	
	
	Boolean findByDate(LocalDate date);
	Boolean existsByDateAndTime(LocalDate date,LocalTime time);
	
	
	
	
	
	public static final String FIND_PROJECTS="select * from reservation r,patients p where r.Doctorid=:doctorid and r.date=:date and r.patientid=p.id";	
	@Query(nativeQuery = true,value=FIND_PROJECTS)
	List<ReservationEntity> findaa(Integer doctorid,LocalDate date);
	
	@Query(nativeQuery = true,value="select * from reservation where Doctorid=:doctorid and date=:date")
	List<ReservationEntity> findbooked(Integer doctorid,LocalDate date);
	
	@Query(nativeQuery = true,value="select * from reservation where Doctorid=:doctorId and date between :fromDate and :toDate")
	List<ReservationEntity> CsvFileDoctor(Integer doctorId,LocalDate fromDate,LocalDate toDate);
	
	@Query(nativeQuery = true,value="select * from reservation where Patientid=:PatientId and date between :fromDate and :toDate")
	List<ReservationEntity> CsvFilePatient(Integer PatientId,LocalDate fromDate,LocalDate toDate);
	
	@Query( nativeQuery = true,value ="select * from Reservation where Doctorid=:DoctorId && Patientid=:PatientId&&date=:date")
	Optional<ReservationEntity> existDPD(Integer DoctorId,Integer PatientId,LocalDate date);
	
	@Query( nativeQuery = true,value ="select * from Reservation where Doctorid=:DoctorId && date=:date&&time=:time")
	Optional<ReservationEntity> existDDT(Integer DoctorId,LocalDate date,LocalTime time);
	
	@Query( nativeQuery = true,value ="select * from Reservation where Patientid=:PationtId && date=:date&&time=:time")
	Optional<ReservationEntity> existPDT(Integer PationtId,LocalDate date,LocalTime time);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE Reservation  SET status=:status where id=:appointment",nativeQuery = true)
	Integer updateStatus (Integer appointment,Integer status);
	
	
	@Query(value="select count(status) from reservation where status =0 and Patientid=:patientId and Doctorid=:doctorId",nativeQuery = true)
	Integer visitedCount (Integer doctorId,Integer patientId);
	
	
	
}

