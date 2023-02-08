package com.fmi.oop_ii.models.account;

import java.time.LocalDate;
import java.time.Period;

public class Account {
	private String username;
	private LocalDate birthDate;

	public Account(String username, LocalDate birthDate) {
		this.username = username;
		this.birthDate = birthDate;
	}
	
	public String getUsername() {
		return username;
	}
	
	public LocalDate getBirthDate() {
		return birthDate;
	}
	
	public int getAge() {
		Period period = Period.between(birthDate, LocalDate.now());
		
		return period.getYears();
	}
}
