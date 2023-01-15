package com.example.dentalClinic.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.dentalClinic.dto.ReservationDto;
import com.example.dentalClinic.entity.ReservationEntity;
import com.example.dentalClinic.repository.PatientRepository;


@Component
public class ReservationMapper  {

	@Autowired
	PatientRepository repo;
	
	public ReservationDto toDto(ReservationEntity reservation)
	{
		return ReservationDto.builder()
		.time(reservation.getTime())
		.patientId(reservation.getPatientid())
		.patientName(repo.findById(reservation.getPatientid()).get().getName())
		.build();
	}
	
	public List<ReservationDto> toDtos(List<ReservationEntity> reservation)
	{	 
		return (reservation.stream().sorted().map(i -> toDto(i)).collect(Collectors.toList()));
	}
	
	
}
