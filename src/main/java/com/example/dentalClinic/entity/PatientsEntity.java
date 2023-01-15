package com.example.dentalClinic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Table(name = "Patients")
@Entity
public class PatientsEntity implements ParentClass{
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Pattern(regexp = "^[a-zA-Z0-9_]{6,12}$",message = " must be of 6 to 12 length with no special characters")
	@Column(name="username")
	@NotBlank(message = "Username cannot be null or empty")
	private String username;
	
	@Length(min=8,message = "Must be at Least 8 Characters")
	@Column(name="password")
	@NotBlank(message = "Password cannot be null or empty")
	private String password;
	
	@Pattern(regexp = "^[a-zA-Z0-9_]{3,12}$",message = " must be of 3 to 12 length with no special characters")
	@Column(name="name")
	@NotBlank(message = "Name cannot be null or empty")
	private String name;
	
	@Pattern(regexp = "[0-9]+",message = "Must be only Integer numbers")
	@Column(name="phone_number")
	@NotBlank(message = "phone_number cannot be null or empty")
	@Length(min = 10,max = 10,message = "phoneNumber must be exactly 10 numbers")
	private String phoneNumber;

	@Column(name="age")
	@NotNull(message = "Age cannot be null or empty")
	@Min(value = 1,message = "Age cannot be less than 1")
	@Max(value = 100,message = "Age cannot be greater than 100")
	private Integer age;
	
	
	@Column(name="gender")
	@Pattern(regexp = "^[MmFf]{1}$",message = "The gender must be only m/f")
	@NotBlank(message = "Gender cannot be null or empty")
	private String gender;
	
	
	@JsonIgnore
	@Transient
	private final String role="patient";


	
	
}
