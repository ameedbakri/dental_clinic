package com.example.dentalClinic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "Doctors")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorsEntity implements ParentClass{
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Pattern(regexp = "^[a-zA-Z0-9_]{6,12}$",message = "Must be of 6 to 12 length with no special characters")
	@Column(name="username")
	@NotBlank(message = "Cannot be null or empty")
	private String username;
	

	@Column(name="password")
	@NotBlank(message = "Cannot be null or empty")
	@Length(min=8,message = "Must be at Least 8 Characters")
	private String password;
	
	@Pattern(regexp = "^[a-zA-Z0-9_]{3,12}$",message = " Must be of 3 to 12 length with no special characters")
	@Column(name="name")
	@NotBlank(message = "Cannot be null or empty")
	private String name;
	
	@Column(name="national_ID")
	@NotBlank(message = "Cannot be null or empty")
	@Pattern(regexp = "[0-9]+",message = "Must be only Integer numbers")
	@Length(min = 10,max = 10,message = "Must be exactly 10 numbers")
	private String nationalId;
	
	@Column(name="specialty")
	@NotBlank(message = "Cannot be null or empty")
	private String specialty;
	
	@Column(name="phone_number")
	@NotBlank(message = "Cannot be null or empty")
	@Pattern(regexp = "[0-9]+",message = "Must be only Integer numbers")
	@Length(min = 10,max = 10,message = "Must be exactly 10 numbers")
	private String phoneNumber;
	
	@JsonIgnore
	@Transient
	private final String role="doctor";


	
}
