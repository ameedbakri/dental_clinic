package com.example.dentalClinic.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.dentalClinic.entity.DoctorsEntity;
import com.example.dentalClinic.entity.PatientsEntity;
import com.example.dentalClinic.repository.DoctorsRepository;
import com.example.dentalClinic.repository.PatientRepository;

@Service
public class PatientDetailsService implements UserDetailsService {

	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private DoctorsRepository doctorsRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		
		PatientsEntity patient=patientRepository.findByUsername(username);
		DoctorsEntity doctor=doctorsRepository.findByUsername(username);
		if(patient!=null)
		{
			return new PatientDetails(patient);
		}
		return new PatientDetails(doctor);
		
		
	}
	

}
