package com.fmi.oop_i.card;

import java.time.LocalDate;
import java.util.Objects;

public abstract class AbstractCard implements Card{
	private static final int MAX_BAD_PIN_ATTEMPTS = 3;
	
	private final String cardType;
	private final String number;
	private int pin;
	private final LocalDate expirationDate;
	private int badAttempts;
	private boolean blocked;

	public AbstractCard(String cardType, String number, int pin, LocalDate expirationDate) {
		this.cardType = cardType;
		this.number = number;
		this.pin = pin;
		this.expirationDate = expirationDate;
		this.badAttempts = 0;
		this.blocked = false;
	}
	
	@Override
	public String getType() {
		return cardType;
	}

	@Override
	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	@Override
	public boolean checkPin(int pin) {
		if (isBlocked()) {
			throw new IllegalStateException("Cannot attempt check pin of a blocked card! Card number: " + this.number);
		}
		
		boolean isCorrect = (this.pin == pin);
		processAttempt(isCorrect);
		
		return isCorrect;
	}

	//think of a better name
	private void processAttempt(boolean isCorrect) {
		if (!isCorrect) {
			this.badAttempts++;
			
			if (this.badAttempts >= MAX_BAD_PIN_ATTEMPTS) {
				this.block();
			}
		} else {
			this.badAttempts = 0;
		}
	}

	@Override
	public boolean isBlocked() {
		return blocked;
	}
	
	@Override
	public boolean isExpired() {
		return expirationDate.isBefore(LocalDate.now());
	}

	@Override
	public void block() {
		this.blocked = true;		
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null) {
			return false;
		}
		
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		AbstractCard other = (AbstractCard) obj;
		
		return Objects.equals(number, other.number);
	}
}
