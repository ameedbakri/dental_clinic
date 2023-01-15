package com.example.dentalClinic.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.dentalClinic.entity.DoctorsEntity;
import com.example.dentalClinic.entity.PatientsEntity;

import lombok.Data;

@Data
public class PatientDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private String password;
//	private boolean active;
	private List<GrantedAuthority> authorities;
	public PatientDetails(PatientsEntity patient)
	{
		this.name=patient.getUsername();
		this.password=patient.getPassword();
//		this.active=user.isActive();
		this.authorities=Arrays.stream(patient.getRole().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}
	
	public PatientDetails(DoctorsEntity doctor)
	{
		this.name=doctor.getUsername();
		this.password=doctor.getPassword();
//		this.active=user.isActive();
		this.authorities=Arrays.stream(doctor.getRole().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}
	public PatientDetails()
	{
	
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	

}
