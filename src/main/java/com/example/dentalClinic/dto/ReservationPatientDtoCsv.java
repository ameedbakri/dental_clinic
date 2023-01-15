package com.example.dentalClinic.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationPatientDtoCsv {

	private Integer doctorId;
	private String doctorName;
	private String phoneNumber;
	private String speciality;
	private LocalDate date;
	private LocalTime time;
}
