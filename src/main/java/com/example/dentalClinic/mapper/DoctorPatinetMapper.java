package com.example.dentalClinic.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.dentalClinic.dto.DoctorPatinetDto;
import com.example.dentalClinic.entity.DoctorsEntity;

@Component
public class DoctorPatinetMapper {
	
	public DoctorPatinetDto toDto(DoctorsEntity doctor)
	{
		return  DoctorPatinetDto.builder()
				.doctorId(doctor.getId())
				.name(doctor.getName())
				.specialty(doctor.getSpecialty())
				.phoneNumber(doctor.getPhoneNumber())
				.build();
	}
	public List<DoctorPatinetDto> toDtos(List<DoctorsEntity> doctor)
	{
		return doctor.stream().map(this::toDto).collect(Collectors.toList());
	}
}
