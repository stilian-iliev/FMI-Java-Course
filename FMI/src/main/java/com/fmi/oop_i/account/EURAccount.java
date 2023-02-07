package com.fmi.oop_i.account;

public class EURAccount extends Account {
	public static final String CURRENCY = "EUR";
	
	public EURAccount(String IBAN) {
		this(IBAN, 0);
	}

	public EURAccount(String IBAN, double amount) {
		super(IBAN, amount, CURRENCY);
	}

}
