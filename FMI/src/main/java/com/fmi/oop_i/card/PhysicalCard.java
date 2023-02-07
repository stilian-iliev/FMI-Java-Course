package com.fmi.oop_i.card;

import java.time.LocalDate;

public class PhysicalCard extends AbstractCard{
	public static final String TYPE = "PHYSICAL";

	public PhysicalCard(String number, int pin, LocalDate expirationDate) {
		super(TYPE, number, pin, expirationDate);
	}

}
