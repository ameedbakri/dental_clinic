package com.example.dentalClinic.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.dentalClinic.dto.ReservationPatientDtoCsv;
import com.example.dentalClinic.entity.ReservationEntity;
import com.example.dentalClinic.repository.DoctorsRepository;

@Component
public class ReservationPatientMapperCsv {

	@Autowired
	DoctorsRepository repo;
	public ReservationPatientDtoCsv toDto(ReservationEntity reservation)
	{
		return ReservationPatientDtoCsv.builder()
				.doctorId(reservation.getDoctorid())
				.doctorName(repo.findById(reservation.getDoctorid()).get().getName())
				.phoneNumber(repo.findById(reservation.getDoctorid()).get().getPhoneNumber())
				.speciality(repo.findById(reservation.getDoctorid()).get().getSpecialty())
				.date(reservation.getDate())
				.time(reservation.getTime())
				.build();
	}
	public List<ReservationPatientDtoCsv> toDtos(List<ReservationEntity> reservation)
	{
		return reservation.stream().sorted().map(x->toDto(x)).collect(Collectors.toList());
	}
}
