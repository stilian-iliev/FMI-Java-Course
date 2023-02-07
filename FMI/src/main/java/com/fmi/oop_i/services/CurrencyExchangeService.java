package com.fmi.oop_i.services;

import com.fmi.oop_i.account.BGNAccount;
import com.fmi.oop_i.account.EURAccount;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CurrencyExchangeService {
	public static final double EUR_TO_BGN_EXCHANGE_RATE = 1.95583;
	public static final String[] SUPPORTED_CURRENCIES = new String[] { BGNAccount.CURRENCY, EURAccount.CURRENCY };

	public static double convertEurToBgn(double amount) {
		return amount * EUR_TO_BGN_EXCHANGE_RATE;
	}

	public static double convertBgnToEur(double amount) {
		return amount / EUR_TO_BGN_EXCHANGE_RATE;
	}

	public static double convert(double amount, String src, String dest) {
		if (src.equals(dest)) {
			return amount;
		} else if (src.equals(BGNAccount.CURRENCY) && dest.equals(EURAccount.CURRENCY)) {
			return convertBgnToEur(amount);
		} else if (src.equals(EURAccount.CURRENCY) && dest.equals(BGNAccount.CURRENCY)) {
			return convertEurToBgn(amount);
		}
		// say which currencies allowed in the exception message
		throw new UnsupportedOperationException(
				String.format("Currency convertion from %s to %s not possible. Supported currencies are %s.", 
						src, dest, supportedCurrenciesToString()));
	}

	private static String supportedCurrenciesToString() {
		return Arrays.stream(SUPPORTED_CURRENCIES)
				.map(String::valueOf)
				.collect(Collectors.joining(", "));
	}
}
