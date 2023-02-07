package com.fmi.oop_i.account;

public class BGNAccount extends Account {
	public static final String CURRENCY = "BGN";

	public BGNAccount(String IBAN) {
		this(IBAN, 0);
	}
	
	public BGNAccount(String IBAN, double amount) {
        super(IBAN, amount, CURRENCY);
    }

}
