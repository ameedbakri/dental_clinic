package com.example.dentalClinic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorPatinetDto {
	private Integer doctorId;
	private String name;
	private String specialty;
	private String phoneNumber;	
}
