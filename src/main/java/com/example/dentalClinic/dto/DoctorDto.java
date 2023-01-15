package com.example.dentalClinic.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDto {
	
	private Integer doctorId;
	private String username;
	private String name;
	private String nationalId;
	private String specialty;
	private String phoneNumber;	
	
}

