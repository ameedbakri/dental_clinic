package com.example.dentalClinic.mapper;

import org.springframework.stereotype.Component;

import com.example.dentalClinic.dto.DoctorDto;
import com.example.dentalClinic.entity.DoctorsEntity;

@Component
public class DoctorMapper {
	
	public DoctorDto toDto(DoctorsEntity doctor)
	{
		return DoctorDto.builder()
				.doctorId(doctor.getId())
				.username(doctor.getUsername())
				.name(doctor.getName())
				.nationalId(doctor.getNationalId())
				.specialty(doctor.getSpecialty())
				.phoneNumber(doctor.getPhoneNumber())
				.build();
	}
}
