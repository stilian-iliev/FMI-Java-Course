package com.fmi.oop_i.card;

import java.time.LocalDate;

public class VirtualOneTimeCard extends AbstractCard {
	public static final String TYPE = "VIRTUALONETIME";

	public VirtualOneTimeCard(String number, int pin, LocalDate expirationDate) {
		super(TYPE, number, pin, expirationDate);
	}	
}
