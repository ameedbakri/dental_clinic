package com.example.dentalClinic.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.dentalClinic.apiHandler.ApiResult;
import com.example.dentalClinic.entity.PatientsEntity;
import com.example.dentalClinic.entity.ReservationEntity;
import com.example.dentalClinic.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	PatientService service;
	
	
	@PostMapping("/Register")
	public ApiResult RegisterPatient(@RequestBody @Valid PatientsEntity patient)
	{
		return service.RegisterPatient(patient);
	}
	
	@PutMapping("update")
	public ApiResult updatePatient(@RequestBody PatientsEntity patient)
	{
		return service.updatePatient(patient);
	}
	
	@GetMapping("/listOfDoctors")
	public ApiResult getListOfDoctors()
	{
		return service.findAllDoctors();
	}
	
	@GetMapping("/AvailabeDoctors")
	public ApiResult AvailableDoctors(@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate date
			,@RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime  time)
	{
		return service.findAvailableDoctors(date, time);
	}
	
	@PostMapping("/Appointment")
	public ApiResult Appointment(@Valid @RequestBody ReservationEntity reservation)
	{
		return service.Appointment(reservation);
	}
	
	@DeleteMapping("/cancelAppointment")
	public ApiResult cancelAppointment(@RequestParam Integer AppointmentId)
	{
		return service.cancelAppointment(AppointmentId);
	}
	
	@GetMapping("/CreateCsvFile")
	public ApiResult CreateCsvFile(@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate FromDate
								  ,@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate toDate)
	{
		return service.CreatCsvFile(FromDate,toDate);
	}

}
