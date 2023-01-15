package com.example.dentalClinic.dto;

import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto implements Comparable<ReservationDto> {
	
	private LocalTime time;
	private Integer patientId;
	private String patientName;
	
	
	@Override
	public int compareTo(ReservationDto o) {
		if(this.time.isBefore(o.getTime()))
		{
			return 1;
		}else if(this.time.isAfter(o.getTime()))
		{
			return -1;
		}else
		return 0;
	}
}
