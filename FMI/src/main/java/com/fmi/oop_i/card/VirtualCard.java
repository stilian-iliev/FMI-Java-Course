package com.fmi.oop_i.card;

import java.time.LocalDate;

public class VirtualCard extends AbstractCard{
	public static final String TYPE = "VIRTUALPERMANENT";

	public VirtualCard(String number, int pin, LocalDate expirationDate) {
		super(TYPE, number, pin, expirationDate);
	}

}
