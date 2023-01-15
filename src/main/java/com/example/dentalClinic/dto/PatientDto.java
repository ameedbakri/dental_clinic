package com.example.dentalClinic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {

	private Integer patientId;
	private String name;
	private Integer age;
	private String gender;
	private String phoneNumber;
}
