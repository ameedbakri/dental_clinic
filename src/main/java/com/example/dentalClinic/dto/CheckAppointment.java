package com.example.dentalClinic.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckAppointment {

	@NotNull(message = "Cannot be null or empty")
	@Min(value = 1,message = "Cannot be less than 1")
	private Integer appointmentId;
	@NotNull(message = "Cannot be null or empty")
	private Integer status;
}
