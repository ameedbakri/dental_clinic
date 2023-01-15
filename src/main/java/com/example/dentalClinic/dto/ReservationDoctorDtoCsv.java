package com.example.dentalClinic.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationDoctorDtoCsv {
	private Integer patientId;
	private String patientName;
	private Integer Patientage;
	private String Patientgender;
	private String phoneNumber;
	private LocalDate date;
	private LocalTime time;
}
