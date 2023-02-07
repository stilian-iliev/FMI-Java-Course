package com.fmi.oop_i.account;

import com.fmi.oop_i.services.CurrencyExchangeService;

import java.util.Objects;

public abstract class Account {
	private double amount;
	private String IBAN;
	private String currency;

	public Account(String IBAN, double amount, String currency) {
		this.IBAN = IBAN;
		this.amount = amount;
		this.currency = currency;
	}

	public String getCurrency() {
		return this.currency;
	}

	public double getAmount() {
		return this.amount;
	}

	public void withdraw(double amountToWithdraw) {
		if (!hasEnoughtMoney(amountToWithdraw)) {
			// tell the parameters in the exception message
			throw new IllegalStateException(String.format(
					"Cannot withdraw more than the balance in account %s. Available balance is %d. Withdraw attempt is %d.",
					IBAN, getAmount(), amountToWithdraw));
		}

		setAmount(getAmount() - amountToWithdraw);
	}

	public void deposit(double amountToDeposit) {
		setAmount(getAmount() + amountToDeposit);
	}

	public String getIBAN() {
		return IBAN;
	}

	public boolean hasEnoughtMoney(double minAmount) {
		return getAmount() >= minAmount;
	}

	public boolean hasEnoughtMoney(double minAmount, String srcCurrency) {
		double amountInAccountCurrency = CurrencyExchangeService.convert(minAmount, srcCurrency, getCurrency());

		return hasEnoughtMoney(amountInAccountCurrency);
	}

	private void setAmount(double amount) {
		this.amount = amount;
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

		Account other = (Account) obj;

		return Objects.equals(IBAN, other.IBAN);
	}

	@Override
	public int hashCode() {
		return Objects.hash(IBAN);
	}

}
