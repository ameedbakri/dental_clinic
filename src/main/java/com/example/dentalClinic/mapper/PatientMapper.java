package com.example.dentalClinic.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.example.dentalClinic.dto.PatientDto;
import com.example.dentalClinic.entity.PatientsEntity;

@Component
public class PatientMapper {

	public PatientDto toDto(PatientsEntity patient)
	{
		return PatientDto.builder()
				.patientId(patient.getId())
				.name(patient.getName())
				.age(patient.getAge())
				.gender(patient.getGender())
				.phoneNumber(patient.getPhoneNumber())
				.build();
	}
	
	public List<PatientDto> toDtos(List<PatientsEntity> patient)
	{	 
		return (patient.stream().map(this::toDto).collect(Collectors.toList()));
	}
}
