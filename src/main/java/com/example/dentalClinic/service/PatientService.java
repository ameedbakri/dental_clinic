package com.example.dentalClinic.service;

import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dentalClinic.apiHandler.ApiExceptionHandler;
import com.example.dentalClinic.apiHandler.ApiRequestException;
import com.example.dentalClinic.apiHandler.ApiResult;
import com.example.dentalClinic.entity.PatientsEntity;
import com.example.dentalClinic.entity.ReservationEntity;
import com.example.dentalClinic.mapper.DoctorPatinetMapper;
import com.example.dentalClinic.mapper.PatientMapper;
import com.example.dentalClinic.repository.DoctorsRepository;
import com.example.dentalClinic.repository.PatientRepository;
import com.example.dentalClinic.repository.ReservationRepository;

@Service
public class PatientService {

	@Autowired 
	private PatientRepository patientRepository;
	
	@Autowired
	private PatientMapper patientMapper;
	
	@Autowired
	private DoctorPatinetMapper DPMapper;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private DoctorsRepository doctorRepo;
	
	
	@Autowired
	private ApiExceptionHandler apiException;
	
	public ApiResult RegisterPatient(PatientsEntity patient)
	{
		apiException.Rigester(patient);
		patientRepository.save(patient);
		return new ApiRequestException().api("Patient was added");
	}
	public ApiResult updatePatient(PatientsEntity patient)
	{
		apiException.updatePatient(patient);
		patientRepository.save(patient);
		return new ApiRequestException().api("Patient was updated");
	}
	public ApiResult findAllPaTient()
	{
		return new ApiRequestException().api(patientMapper.toDtos(patientRepository.findAll()));
	}
	public ApiResult getPatientById(Integer patientId)
	{
		if(!patientRepository.existsById(patientId))
		{
			if(patientId<=0)
			{
			 throw new ApiRequestException("the PatientId Cannot be less than 1");
			}
			throw new ApiRequestException("the PatientId does not exist");
		}
		return new ApiRequestException().api(patientMapper.toDto(patientRepository.findById(patientId).get()));
	}
	
	public ApiResult Appointment(ReservationEntity reservation)
	{
		apiException.reservationException(reservation);
		reservationRepository.save(reservation);
		return new ApiRequestException().api("Reservation was Success");
	}
	public ApiResult cancelAppointment(Integer Appointmentid)
	{
		apiException.CancelReservationPatient(Appointmentid);
		reservationRepository.deleteById(Appointmentid);
		return new ApiRequestException().api("Reservation was Canceled");
	}
	public ApiResult findAllDoctors()
	{
		return new ApiRequestException().api(DPMapper.toDtos(doctorRepo.findAll()));
	}
	public ApiResult findAvailableDoctors(LocalDate date,LocalTime time)
	{
		if(date.isBefore(LocalDate.now()))
		{
			throw new ApiRequestException("You cann't check in past date");
		}
		if(date.isEqual(LocalDate.now()))
		{
			if(time.isBefore(LocalTime.now()))
			{
				throw new ApiRequestException("You cann't check in past time");
			}
		}
		return new ApiRequestException().api(DPMapper.toDtos(doctorRepo.findAvailableDoctor(date,time)));
	}
	public ApiResult CreatCsvFile(LocalDate FromDate, LocalDate toDate)
	{
		apiException.createCsvPatientFile(FromDate, toDate);
		return new ApiRequestException().api("The file was Added");
	}
}