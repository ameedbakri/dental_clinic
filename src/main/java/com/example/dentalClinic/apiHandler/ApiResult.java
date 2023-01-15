package com.example.dentalClinic.apiHandler;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ApiResult {
	private String StatusCode;
	private String 	StatusDescription;
	private Object Result;

	

	

}
