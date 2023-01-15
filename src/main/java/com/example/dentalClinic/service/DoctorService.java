package com.example.dentalClinic.service;

import java.time.LocalDate;
import com.example.dentalClinic.dto.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.example.dentalClinic.apiHandler.ApiExceptionHandler;
import com.example.dentalClinic.apiHandler.ApiRequestException;
import com.example.dentalClinic.apiHandler.ApiResult;
import com.example.dentalClinic.entity.DoctorsEntity;
import com.example.dentalClinic.entity.ReservationEntity;
import com.example.dentalClinic.mapper.ReservationMapper;
import com.example.dentalClinic.repository.DoctorsRepository;
import com.example.dentalClinic.repository.PatientRepository;
import com.example.dentalClinic.repository.ReservationRepository;


@Service
public class DoctorService {
	
	@Autowired
	private DoctorsRepository doctorsRepository;

	
	@Autowired
	private ReservationMapper reservationMapper;
	
	@Autowired 
	private PatientRepository patientRepository;
	
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private ApiExceptionHandler apiexception;
	
	public ApiResult rigesterDoctor(DoctorsEntity doctor)
	{
		apiexception.Rigester(doctor);
		if(doctorsRepository.existsByNationalId(doctor.getNationalId()))
		{
			throw new ApiRequestException("This National_id is already used");
		}
		doctorsRepository.save(doctor);
		return new ApiRequestException().api("doctor was added");
	}
	
	public ApiResult updateDoctor(DoctorsEntity doctor)
	{
		apiexception.updateDoctor(doctor);
		doctorsRepository.save(doctor);
		return new ApiRequestException().api("doctor was updated");
	}
	
	public ApiResult Appointment(ReservationEntity reservation)
	{
		apiexception.reservationException(reservation);
		reservationRepository.save(reservation);
		return new ApiRequestException().api("Reservation was Success");
		
	}
	
	public ApiResult cancelAppointment(Integer Appointmentid)
	{
		apiexception.CancelReservationDoctor(Appointmentid);
		reservationRepository.deleteById(Appointmentid);
		return new ApiRequestException().api("Reservation was Canceled");
	}
	
	public ApiResult AvailableTime()
	{
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		DateTimeFormatter df=DateTimeFormatter.ofPattern("HH:mm");
		Set<String> availableTimes = Stream.of(AvailableTime.values()).map(i -> i.getTime()).collect(Collectors.toSet());
		List<String> reservation=reservationRepository.findaa(doctorsRepository.findByUsername(auth.getName()).getId(),LocalDate.now())
				.stream()
				.map(i -> df.format(i.getTime()))
				.collect(Collectors.toList());
		for(final String time : reservation)
		{
			if(availableTimes.contains(time));
			{
				availableTimes.remove(time);
			}
		}
		return new ApiRequestException().api(availableTimes.stream().map(x->"time:"+x).sorted().collect(Collectors.toList()));
	}
	
	public ApiResult BookedTimeline()
	{
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		return new ApiRequestException().api((reservationMapper.toDtos(reservationRepository.findbooked(doctorsRepository.findByUsername(auth.getName()).getId(), LocalDate.now()))));
	}

	public ApiResult checkAppointment(Integer appointmentId,Integer status)
	{
		apiexception.checkAppointment(appointmentId,status);
		reservationRepository.updateStatus(appointmentId,status);
		return new ApiRequestException().api("Appointment was update");
	}
	
	public ApiResult visitedCount(Integer patientId)
	{
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		if(!patientRepository.existsById(patientId))
		{
			if(patientId<=0)
			{
				throw new ApiRequestException("The patientId must be greater than 1");
			}
			throw new ApiRequestException("The patientId does not exist");
		}
		Integer id=doctorsRepository.findByUsername(auth.getName()).getId();
		return new ApiRequestException().api("count:"+reservationRepository.visitedCount(id, patientId));
	}
	
	public ApiResult CreatCsvFile(LocalDate FromDate, LocalDate toDate)
	{
		apiexception.createCsvDoctorFile(FromDate, toDate);
		return new ApiRequestException().api("The file was Added");
	}
	
}
