package com.example.dentalClinic.apiHandler;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.regex.Pattern;

import javax.persistence.TransactionRequiredException;

import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.example.dentalClinic.dto.ReservationDoctorDtoCsv;
import com.example.dentalClinic.dto.ReservationPatientDtoCsv;
import com.example.dentalClinic.entity.DoctorsEntity;
import com.example.dentalClinic.entity.ParentClass;
import com.example.dentalClinic.entity.PatientsEntity;
import com.example.dentalClinic.entity.ReservationEntity;
import com.example.dentalClinic.mapper.ReservationDoctorMapperCsv;
import com.example.dentalClinic.mapper.ReservationPatientMapperCsv;
import com.example.dentalClinic.repository.DoctorsRepository;
import com.example.dentalClinic.repository.PatientRepository;
import com.example.dentalClinic.repository.ReservationRepository;

@RestControllerAdvice
@ControllerAdvice
public class ApiExceptionHandler extends ExceptionHandlerExceptionResolver   {
	
	@Autowired 
	private PatientRepository patientRepository;
	
	@Autowired
	private ReservationDoctorMapperCsv csvDoctor;
	@Autowired
	private ReservationPatientMapperCsv csvpatient;
	
	
	@Autowired
	private DoctorsRepository doctorRepo;
	
	@Autowired
	private ReservationRepository reserRepo;
	
	private PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	@ExceptionHandler(value = {ApiRequestException.class})
	public ResponseEntity<Object> handleApiRequestException(ApiRequestException e)
	{
		ApiResult apiException=	new ApiResult();
		apiException.setStatusCode("1");
		apiException.setStatusDescription("error");
		apiException.setResult(e.getMessage());
		return new ResponseEntity<>(apiException,HttpStatus.BAD_REQUEST);
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	public ApiResult handleVlaidationException(MethodArgumentNotValidException ex)
	{
		ApiResult api=new ApiResult();
		Map<String,Object> errors=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fielName=((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fielName, errorMessage);
		});
		api.setStatusCode("1");
		api.setStatusDescription("error");
		api.setResult(errors);
		return api;
	}
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = {HttpMessageNotReadableException.class})
	public ApiResult handleVlaidationException(HttpMessageNotReadableException ex)
	{
		ApiResult api=new ApiResult();
		api.setStatusCode("1");
		api.setStatusDescription("error");
		api.setResult(" Required request body is missing");
		return api;
	}
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
	public ApiResult handleVlaidationException(MethodArgumentTypeMismatchException ex)
	{
		ApiResult api=new ApiResult();
		api.setStatusCode("1");
		api.setStatusDescription("error");
		api.setResult(ex.getMessage());
		return api;
	}
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = {MissingServletRequestParameterException.class})
	public ApiResult handleVlaidationException(MissingServletRequestParameterException ex)
	{
		ApiResult api=new ApiResult();
		api.setStatusCode("1");
		api.setStatusDescription("error");
		api.setResult(ex.getMessage());
		return api;
	}
	@ResponseStatus(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
	public ApiResult handleVlaidationException(HttpMediaTypeNotSupportedException ex)
	{
		ApiResult api=new ApiResult();
		api.setStatusCode("1");
		api.setStatusDescription("error");
		api.setResult(ex.getMessage());
		return api;
	}
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = {SQLSyntaxErrorException.class})
	public ApiResult handleVlaidationException(SQLSyntaxErrorException ex)
	{
		ApiResult api=new ApiResult();
		api.setStatusCode("1");
		api.setStatusDescription("error");
		api.setResult(ex.getMessage());
		return api;
	}
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = {NullPointerException.class})
	public ApiResult handleVlaidationException(NullPointerException ex)
	{
		ApiResult api=new ApiResult();
		api.setStatusCode("1");
		api.setStatusDescription("error");
		api.setResult(ex.getMessage());
		return api;
	}
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = {ConcurrentModificationException.class})
	public ApiResult handleVlaidationException(ConcurrentModificationException ex)
	{
		ApiResult api=new ApiResult();
		api.setStatusCode("1");
		api.setStatusDescription("error");
		api.setResult(ex.getMessage());
		return api;
	}
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = {SQLException.class})
	public ApiResult handleVlaidationException(SQLException ex)
	{
		ApiResult api=new ApiResult();
		api.setStatusCode("1");
		api.setStatusDescription("error");
		api.setResult(ex.getMessage());
		return api;
	}
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = {DataIntegrityViolationException.class})
	public ApiResult handleVlaidationException(SQLIntegrityConstraintViolationException ex)
	{
		ApiResult api=new ApiResult();
		api.setStatusCode("1");
		api.setStatusDescription("error");
		api.setResult(ex.getMessage());
		return api;
	}
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = {TransactionRequiredException.class})
	public ApiResult handleVlaidationException(TransactionRequiredException ex)
	{
		ApiResult api=new ApiResult();
		api.setStatusCode("1");
		api.setStatusDescription("error");
		api.setResult(ex.getMessage());
		return api;
	}
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = {JpaSystemException.class})
	public ApiResult handleVlaidationException(GenericJDBCException ex)
	{
		ApiResult api=new ApiResult();
		api.setStatusCode("1");
		api.setStatusDescription("error");
		api.setResult(ex.getMessage());
		return api;
	}
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = {NoSuchElementException.class})
	public ApiResult handleVlaidationException(NoSuchElementException ex)
	{
		ApiResult api=new ApiResult();
		api.setStatusCode("1");
		api.setStatusDescription("error");
		api.setResult(ex.getMessage());
		return api;
	}
	@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
	public ApiResult handleVlaidationException(HttpRequestMethodNotSupportedException ex)
	{
		ApiResult api=new ApiResult();
		api.setStatusCode("1");
		api.setStatusDescription("error");
		api.setResult(ex.getMessage());
		return api;
	}
	
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	@ExceptionHandler(value = {org.springframework.security.access.AccessDeniedException.class})
	public ApiResult handleVlaidationException(org.springframework.security.access.AccessDeniedException  ex)
	{
		ApiResult api=new ApiResult();
		api.setStatusCode("1");
		api.setStatusDescription("error");
		api.setResult(ex.getMessage());
		return api;
	}

	public void Rigester(ParentClass parent)
	{	
		if(parent.getId()!=null)
		{
			throw new ApiRequestException("Cannot send Id, it is Automatic added");
		}
		if(doctorRepo.existsByUsername(parent.getUsername())||patientRepository.existsByUsername(parent.getUsername()))
		{
			throw new ApiRequestException("This userName is already used");
		}
		if(doctorRepo.existsByPhoneNumber(parent.getPhoneNumber())||patientRepository.existsByPhoneNumber(parent.getPhoneNumber()))
		{
			throw new ApiRequestException("This phoneNumber is already used");
		}
		if(Character.isDigit(parent.getName().charAt(0)))
		{
			 throw new ApiRequestException("Name Cannot Start Wtih Number");
		}
		if(Character.isDigit(parent.getUsername().charAt(0)))
		{
			throw new ApiRequestException("UserName Cannot Start With Number");
		}
		if(!(parent.getPhoneNumber().charAt(0)=='0'&&parent.getPhoneNumber().charAt(1)=='7'&&
		 (parent.getPhoneNumber().charAt(2)=='9'||parent.getPhoneNumber().charAt(2)=='8'||parent.getPhoneNumber().charAt(2)=='7')))
		{
			throw new ApiRequestException("The PhoneNumber should be start with 079/078/077");
		}
		String encodedPassword=passwordEncoder.encode(parent.getPassword());
		parent.setPassword(encodedPassword);
	}
	
	public void reservationException(ReservationEntity reservation)
	{
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		if(doctorRepo.existsByUsername(auth.getName()))
		{
			if(reservation.getPatientid()==null)
			{
				throw new ApiRequestException("The patientId cannot be null or empty");
			}
			if(reservation.getDoctorid()!=null)
			{
				throw new ApiRequestException("Cannot send doctorId its added when you login");
			}
			reservation.setDoctorid(doctorRepo.findByUsername(auth.getName()).getId());
		}
		if(patientRepository.existsByUsername(auth.getName()))
		{
			if(reservation.getDoctorid()==null)
			{
				throw new ApiRequestException("The doctorId cannot be null or empty");
			}
			if(reservation.getPatientid()!=null)
			{
				throw new ApiRequestException("Cannot send patientId its added when you login");
			}
			reservation.setPatientid(patientRepository.findByUsername(auth.getName()).getId());
		}
		if(!patientRepository.existsById(reservation.getPatientid()))
		{
			throw new ApiRequestException("PatientId Does not exist");
		}
		if(!doctorRepo.existsById(reservation.getDoctorid()))
		{
			throw new ApiRequestException("DoctorId Does not exist");
		}
		if(reservation.getDate().isBefore(LocalDate.now()))
		{
			throw new ApiRequestException("Cannot reserve on Past Date");
		}
		if(reservation.getTime().isBefore(LocalTime.of(8,00))||reservation.getTime().isAfter(LocalTime.of(16,00)))
		{
			throw new ApiRequestException("Cannot reserve outside working hours.The reservation must be between 8:00 to 16:00");
		}
		if(reservation.getDate().isEqual(LocalDate.now()))
		{
			if(reservation.getTime().isBefore(LocalTime.now()))
			{
				throw new ApiRequestException("Cannot reserve on Past Time");
			}
		}
		Optional<ReservationEntity> optRecord1=reserRepo.existDPD(reservation.getDoctorid(),reservation.getPatientid(), reservation.getDate());
		if(optRecord1.isPresent())
		{
			throw new ApiRequestException("Cannot any patient reserve two session with the same doctor on the same date");
		}
		Optional<ReservationEntity> optRecord2=reserRepo.existDDT(reservation.getDoctorid(),reservation.getDate(),reservation.getTime());
		if(optRecord2.isPresent())
		{
			throw new ApiRequestException("Cannot more patients reserve with same doctor on the same date and at the same time");
		}
		Optional<ReservationEntity> optRecord3=reserRepo.existPDT(reservation.getPatientid(),reservation.getDate(),reservation.getTime());
		if(optRecord3.isPresent())
		{
			throw new ApiRequestException("Cannot the patient reserve more than one doctor at the same time and on the same date");
		}
	}
	public void CancelReservationDoctor(Integer id)
	{
		ReservationEntity res=new ReservationEntity();
		if(id<=0)
		{
			throw new ApiRequestException("The id must be greater than 0");
		}
		if(!reserRepo.existsById(id))
		{
			throw new ApiRequestException("This AppointmentId:"+id+" does not exist");
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name=auth.getName();
		res.setDoctorid(reserRepo.findById(id).get().getDoctorid());
		if(!name.equals(doctorRepo.findById(res.getDoctorid()).get().getUsername()))
		{
			throw new ApiRequestException("Cannot delete reservation not for you");
		}
	}
	public void CancelReservationPatient(Integer id)
	{
		ReservationEntity res=new ReservationEntity();
		if(id<0)
		{
			throw new ApiRequestException("The id must be greater than 0");
		}
		if(!reserRepo.existsById(id))
		{
			throw new ApiRequestException("This AppointmentId:"+id+" does not exist");
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name=auth.getName();
		res.setPatientid(reserRepo.findById(id).get().getPatientid());
		if(!name.equals(patientRepository.findById(res.getPatientid()).get().getUsername()))
		{
			throw new ApiRequestException("Cannot delete reservation not for you");
		}
	}
	public void checkAppointment(Integer id,Integer status)
	{
		ReservationEntity res=new ReservationEntity();
		if(!reserRepo.existsById(id))
		{
			throw new ApiRequestException("This AppointmentId does not exist");
		}
		if(status<0||status>1)
		{
			throw new ApiRequestException("The status must be only 0 or 1");
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name=auth.getName();
		res.setDoctorid(reserRepo.findById(id).get().getDoctorid());
		if(!name.equals(doctorRepo.findById(res.getDoctorid()).get().getUsername()))
		{
			throw new ApiRequestException("Cannot checkAppointment not for you");
		}		
	}
	
	public void updateDoctor(DoctorsEntity doctor)
	{
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		if(doctor.getId()!=null)
		{
			throw new ApiRequestException("The Id is manual added when you login");
		}
		doctor.setId(doctorRepo.findByUsername(auth.getName()).getId());
		if(doctor.getUsername()!=null)
		{
			throw new ApiRequestException("You cannot update the userName");
		}
		if(doctor.getUsername()==null)
		{
			doctor.setUsername(doctorRepo.findById(doctor.getId()).get().getUsername());
		}
		if(doctor.getName()==null)
		{
			doctor.setName(doctorRepo.findById(doctor.getId()).get().getName());
		}
		if(doctor.getName().length()<3||doctor.getName().length()>15)
		{
			throw new ApiRequestException("Name must be between 3-15 character");
		}
		if(doctor.getNationalId()==null)
		{
			doctor.setNationalId(doctorRepo.findById(doctor.getId()).get().getNationalId());
		}else {
		if(Pattern.matches("[0-9]+", doctor.getNationalId())==false)
		{
			throw new ApiRequestException("The NationalId must be only Integer numbers");
		}
		if(doctor.getNationalId().length()!=10)
		{
			throw new ApiRequestException("nationalId must be exactly 10 numbers");
		}
		if(doctorRepo.existsByNationalId(doctor.getNationalId()))
		{
			throw new ApiRequestException("This National_id is already used");
		}
		}
		if(doctor.getPassword()!=null)
		{
			String encodedPassword=passwordEncoder.encode(doctor.getPassword());
			doctor.setPassword(encodedPassword);
		}
		if(doctor.getPassword()==null)
		{
			doctor.setPassword(doctorRepo.findById(doctor.getId()).get().getPassword());
		}
		if(doctor.getPassword().length()<8)
		{
			throw new ApiRequestException("Password must be at least 8 character");
		}
		if(doctor.getPhoneNumber()==null)
		{
			doctor.setPhoneNumber(doctorRepo.findById(doctor.getId()).get().getPhoneNumber());
		}else {
		if(Pattern.matches("[0-9]+", doctor.getPhoneNumber())==false)
		{
			throw new ApiRequestException("The PhoneNumber must be only Integer numbers");
		}
		if(doctor.getPhoneNumber().length()!=10)
		{
			throw new ApiRequestException("phoneNumber must be exactly 10 numbers");
		}
		if(!(doctor.getPhoneNumber().charAt(0)=='0'&&doctor.getPhoneNumber().charAt(1)=='7'&&
		 (doctor.getPhoneNumber().charAt(2)=='9'||doctor.getPhoneNumber().charAt(2)=='8'||doctor.getPhoneNumber().charAt(2)=='7')))
		{
			throw new ApiRequestException("The PhoneNumber must start with 079/078/077");
		}
		if(doctorRepo.existsByPhoneNumber(doctor.getPhoneNumber()))
		{
			throw new ApiRequestException("This PhoneNumber is already used");
		}
		}
		if(doctor.getSpecialty()==null)
		{
			doctor.setSpecialty(doctorRepo.findById(doctor.getId()).get().getSpecialty());
		}
		if(Character.isDigit(doctor.getName().charAt(0)))
		{
			 throw new ApiRequestException("Name Cannot Start Wtih Number");
		}
	}
	public void updatePatient(PatientsEntity patient)
	{
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		if(patient.getId()!=null)
		{
			throw new ApiRequestException("The Id is manual added when you login");
		}
		patient.setId(patientRepository.findByUsername(auth.getName()).getId());
		if(patient.getUsername()!=null)
		{
			throw new ApiRequestException("Cannot update the userName");
		}
		if(patient.getUsername()==null)
		{
			patient.setUsername(patientRepository.findById(patient.getId()).get().getUsername());
		}
		if(patient.getName()==null)
		{
			patient.setName(patientRepository.findById(patient.getId()).get().getName());
		}else{
		if(patient.getName().length()<3||patient.getName().length()>15)
		{
			throw new ApiRequestException("Name must be between 3-15 character");
		}
		if(Character.isDigit(patient.getName().charAt(0)))
		{
			 throw new ApiRequestException("Name Cannot Start Wtih Number");
		}
		}
		if(patient.getPassword()==null)
		{
			patient.setPassword(patientRepository.findById(patient.getId()).get().getPassword());
		}else{
		if(patient.getPassword().length()<8)
		{
			throw new ApiRequestException("Password must be at least 8 character");
		}
		if(patient.getPassword()!=null)
		{
			String encodedPassword=passwordEncoder.encode(patient.getPassword());
			patient.setPassword(encodedPassword);
		}
		}
		if(patient.getPhoneNumber()==null)
		{
			patient.setPhoneNumber(patientRepository.findById(patient.getId()).get().getPhoneNumber());
		}else {
		if(Pattern.matches("[0-9]+", patient.getPhoneNumber())==false)
		{
			throw new ApiRequestException("The PhoneNumber must be only Integer numbers");
		}
		if(patient.getPhoneNumber().length()!=10)
		{
			throw new ApiRequestException("phoneNumber must be exactly 10 numbers");
		}
		
		if(!(patient.getPhoneNumber().charAt(0)=='0'&&patient.getPhoneNumber().charAt(1)=='7'&&
		 (patient.getPhoneNumber().charAt(2)=='9'||patient.getPhoneNumber().charAt(2)=='8'||patient.getPhoneNumber().charAt(2)=='7')))
		{
			throw new ApiRequestException("The PhoneNumber must start with 079/078/077");
		}

		if(patientRepository.existsByPhoneNumber(patient.getPhoneNumber()))
		{
			throw new ApiRequestException("This PhoneNumber is already used");
		}
		}
		if(patient.getAge()==null)
		{
			patient.setAge(patientRepository.findById(patient.getId()).get().getAge());
		}else
		{
		if(patient.getAge()<1||patient.getAge()>100)
		{
			throw new ApiRequestException("Age must be between 1-100");
		}
		}
		if(patient.getGender()==null)
		{
			patient.setGender(patientRepository.findById(patient.getId()).get().getGender());
		}else
		{
		if(!(patient.getGender().equals("m")||patient.getGender().equals("f")||patient.getGender().equals("M")||patient.getGender().equals("F")))
		{
			throw new ApiRequestException("The gender must be only m/f");
		}
		}
	}
	public void createCsvDoctorFile(LocalDate FromDate,LocalDate toDate)
	{
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		Integer doctorId=doctorRepo.findByUsername(auth.getName()).getId();
		List<ReservationDoctorDtoCsv> allReservation=csvDoctor.toDtos(reserRepo.CsvFileDoctor(doctorId,FromDate,toDate));
		if(FromDate.isAfter(toDate))
		{
			throw new ApiRequestException("The fromDate cannot be after the toDate");
		}
		
		HttpHeaders header=new HttpHeaders();
		header.add(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename="+"Doctor.csv");
		File file=new File("D:\\ECLIPSE\\projects\\dentalClinic\\dentalClinic\\DoctorCsv\\Doctor.csv");
		String pathFile=file.getAbsolutePath();
		try(PrintWriter writer=new PrintWriter(file))
		{
			writer.println("PatientId,Name,age,gender,phoneNumber,Date,Time");
			allReservation.forEach(x->
			{
				StringJoiner sb=new StringJoiner(",");
				sb.add(x.getPatientId()!=null?x.getPatientId()+"":"0");
				sb.add(x.getPatientName()!=null?x.getPatientName():"");
				sb.add(x.getPatientage()!=null?x.getPatientage()+"":"0");
				sb.add(x.getPatientgender()!=null?x.getPatientgender():"");
				sb.add(x.getPhoneNumber()!=null?x.getPhoneNumber()+"":"");
				sb.add(x.getDate()!=null?x.getDate()+"":"");
				sb.add(x.getTime()!=null?x.getTime()+"":"");
				writer.println(sb.toString());
			});
			writer.flush();
			
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputStream input=null;
		try
		{
			input=new BufferedInputStream(new FileInputStream(new File(pathFile)));
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		//to return as file
		InputStreamResource resource=new InputStreamResource(input);
		ResponseEntity
		.ok()
		.headers(header)
		.contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
		
	
	}
	public void createCsvPatientFile(LocalDate FromDate,LocalDate toDate)
	{
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		Integer patientId=patientRepository.findByUsername(auth.getName()).getId();
		List<ReservationPatientDtoCsv> allReservation=csvpatient.toDtos(reserRepo.CsvFilePatient(patientId,FromDate,toDate));
		if(FromDate.isAfter(toDate))
		{
			throw new ApiRequestException("The fromDate must be before the toDate");
		}
		
		HttpHeaders header=new HttpHeaders();
		header.add(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename="+"Patient.csv");
		File file=new File("D:\\ECLIPSE\\projects\\dentalClinic\\dentalClinic\\PatientCsv\\Patient.csv");
		String pathFile=file.getAbsolutePath();
		try(PrintWriter writer=new PrintWriter(file))
		{
			writer.println("DoctorId,Name,phoneNumber,specialty,Date,Time");
			allReservation.forEach(x->
			{
				StringJoiner sb=new StringJoiner(",");
				sb.add(x.getDoctorId()!=null?x.getDoctorId()+"":"0");
				sb.add(x.getDoctorName()!=null?x.getDoctorName():"");
				sb.add(x.getPhoneNumber()!=null?x.getPhoneNumber()+"":"0");
				sb.add(x.getSpeciality()!=null?x.getSpeciality():"");
				sb.add(x.getDate()!=null?x.getDate()+"":"");
				sb.add(x.getTime()!=null?x.getTime()+"":"");
				writer.println(sb.toString());
			});
			writer.flush();
			
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputStream input=null;
		try
		{
			input=new BufferedInputStream(new FileInputStream(new File(pathFile)));
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		//to return as file
		InputStreamResource resource=new InputStreamResource(input);
		ResponseEntity
		.ok()
		.headers(header)
		.contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
	}
}
