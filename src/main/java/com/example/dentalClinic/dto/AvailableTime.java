package com.example.dentalClinic.dto;

public enum AvailableTime {
	EIGHT_AM("08:00"), NINE_AM("09:00"), TEN_AM("10:00"),
	ELEVEN_AM("11:00"), TWELVE_PM("12:00"), ONE_PM("13:00"),
	TWO_PM( "14:00"), THREE_PM( "15:00"), FOUR_PM("16:00");

	private final String time;

	private AvailableTime(final String time) {
		this.time = time;
	}
	
	public String getTime() {
		return this.time;
	}
}
