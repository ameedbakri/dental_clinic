package com.example.dentalClinic.controller;

import java.time.LocalDate;

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
import com.example.dentalClinic.dto.CheckAppointment;
import com.example.dentalClinic.entity.DoctorsEntity;
import com.example.dentalClinic.entity.ReservationEntity;
import com.example.dentalClinic.service.DoctorService;
import com.example.dentalClinic.service.PatientService;

@RestController
@RequestMapping("/doctor")
public class DoctorsController {
	@Autowired
	DoctorService service;
	@Autowired
	private PatientService pService;

	@PostMapping("/Register")
	public ApiResult Rigester(@Valid @RequestBody DoctorsEntity doctor) 
	{
		return service.rigesterDoctor(doctor);
	}

	
	@PutMapping("/update")
	public ApiResult update(@RequestBody DoctorsEntity doctor) 
	{
		return service.updateDoctor(doctor);
	}


	@GetMapping("/AvailableTime")
	public ApiResult AvailableTime() 
	{
		return service.AvailableTime();
	}

	
	@GetMapping("/bookedTimeline")
	public ApiResult BookedTimeline()
	{
		return service.BookedTimeline();
	}

	
	@PostMapping("/appointment")
	public ApiResult appointment(@Valid @RequestBody ReservationEntity reservation)
	{
		return pService.Appointment(reservation);
	}


	@DeleteMapping("/cancelAppointment")
	public ApiResult cancelAppointment(@RequestParam Integer AppointmentId) 
	{
		return service.cancelAppointment(AppointmentId);
	}

	@PostMapping("/checkAppointment")
	public ApiResult checkAppointment(@RequestBody @Valid CheckAppointment check) 
	{
		return service.checkAppointment(check.getAppointmentId(), check.getStatus());
	}
	
	@GetMapping("/CreateCsvFile")
	public ApiResult CreateCsvFile(@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fromDate,
			@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate toDate) 
	{
		return service.CreatCsvFile(fromDate, toDate);
	}
	
	@GetMapping("/visitedCount")
	public ApiResult visitedCount(@RequestParam Integer patientId) 
	{
		return service.visitedCount(patientId);
	}
	
	@GetMapping("/getAllPatients")
	public ApiResult getAllPatients() 
	{
		return pService.findAllPaTient();
	}
	
	@GetMapping("/getPatientById")
	public ApiResult getPatientById(@RequestParam Integer patientId) 
	{
		return pService.getPatientById(patientId);
	}

}