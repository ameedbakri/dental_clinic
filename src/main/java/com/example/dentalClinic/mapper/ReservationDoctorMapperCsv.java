package com.example.dentalClinic.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.dentalClinic.dto.ReservationDoctorDtoCsv;
import com.example.dentalClinic.entity.ReservationEntity;
import com.example.dentalClinic.repository.PatientRepository;

@Component
public class ReservationDoctorMapperCsv {
	
	@Autowired
	PatientRepository repository;
	
	public ReservationDoctorDtoCsv toDto(ReservationEntity reservation)
	{
		return ReservationDoctorDtoCsv.builder()
				.patientId(reservation.getPatientid())
				.patientName(repository.findById(reservation.getPatientid()).get().getName())
				.Patientage(repository.findById(reservation.getPatientid()).get().getAge())
				.Patientgender(repository.findById(reservation.getPatientid()).get().getGender())
				.phoneNumber(repository.findById(reservation.getPatientid()).get().getPhoneNumber())
				.date(reservation.getDate())
				.time(reservation.getTime())
				.build();
	}
	public List<ReservationDoctorDtoCsv> toDtos(List<ReservationEntity> reservation)
	{
		return (reservation.stream().sorted().map(i ->toDto(i)).collect(Collectors.toList()));
				
	}
	
}
