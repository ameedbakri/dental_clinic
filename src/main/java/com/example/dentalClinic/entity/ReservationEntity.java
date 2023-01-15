	package com.example.dentalClinic.entity;


import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Reservation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationEntity implements Comparable<ReservationEntity>{

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Null(message = "You can't send Id, it is Automatic added")
	private Integer id;

	@Min(value = 1,message = "Cannot be less than 1")
	@Column(name = "Doctorid")
	private Integer doctorid;
	
	@Min(value = 1,message = "Cannot be less than 1")
	@Column(name = "Patientid")
	private Integer patientid;
	
	@NotNull(message = "Date cannot be null")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name="date")
	private LocalDate date;
	
	@NotNull(message = "Time cannot be null")
	@JsonFormat(pattern = "HH:mm")
	@Column(name="time")
	private LocalTime time;

	@Override
	public int compareTo(ReservationEntity o) {
		if(this.date.isBefore(o.getDate()))
		{
			return -1;
		}else if(this.date.isAfter(o.getDate()))
		{
			return 1;
		}
		if(this.time.isBefore(o.getTime()))
		{
			return -1;
		}else if(this.time.isAfter(o.getTime()))
		{
			return 1;
		}else
		return 0;
	}
	

	
	
}
