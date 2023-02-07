package com.fmi.oop_i;

import com.fmi.oop_i.account.Account;
import com.fmi.oop_i.account.BGNAccount;
import com.fmi.oop_i.card.Card;
import com.fmi.oop_i.card.PhysicalCard;
import com.fmi.oop_i.card.VirtualOneTimeCard;
import com.fmi.oop_i.services.CurrencyExchangeService;
import com.fmi.oop_i.services.ExternalDomainsService;

import java.util.Arrays;
import java.util.Optional;

public class Revolut implements RevolutAPI {
	private Account[] accounts;
	private Card[] cards;
	private final ExternalDomainsService externalDomainsService;
	
	public Revolut(Account[] accounts, Card[] cards) {
        this.accounts = accounts;
        this.cards = cards;
        this.externalDomainsService = new ExternalDomainsService();
    }
	
	@Override
	public boolean pay(Card card, int pin, double amount, String currency) {
		if (!card.getType().equals(PhysicalCard.TYPE)) {
			return false;
		}

		return makePayment(card, pin, amount, currency);
	}

	@Override
	public boolean payOnline(Card card, int pin, double amount, String currency, String shopURL) {
		if (!allowedPayTo(shopURL)) {
			return false;
		}
		
		boolean paymentComplete = makePayment(card, pin, amount, currency);
		
		if (paymentComplete && card.getType().equals(VirtualOneTimeCard.TYPE)) {
			card.block();
		}
		
		return paymentComplete;
	}

	@Override
	public boolean addMoney(Account account, double amount) {
		if (!accountExists(account)) {
			return false;
		}
		
		account.deposit(amount);
		
		return true;
	}

	@Override
	public boolean transferMoney(Account from, Account to, double amount) {
		//combine ifs
		//extract into method
		if(!moneyTransferPossible(from, to, amount)) {
			return false;
		}
		
		from.withdraw(amount);
		double amountInDestinationCurrency = CurrencyExchangeService.convert(amount, from.getCurrency(), to.getCurrency());
		to.deposit(amountInDestinationCurrency);
		
		return true;
	}

	private boolean moneyTransferPossible(Account from, Account to, double amount) {
		return (accountExists(from) && accountExists(to)) && !from.equals(to) && from.hasEnoughtMoney(amount);
	}

	@Override
	public double getTotalAmount() {
		return Arrays.stream(accounts)
					.mapToDouble(acc -> CurrencyExchangeService.convert(acc.getAmount(), acc.getCurrency(), BGNAccount.CURRENCY))
					.sum();
	}
	
	private boolean makePayment(Card card, int pin, double amount, String currency) {
		//todo
		if (!isCardValid(card, pin)) {
			return false;
		}
		
		Optional<Account> accountToWithdrawFrom = findАccount(currency, amount);
		
		if (!accountToWithdrawFrom.isPresent()) {
			return false;
		}
		
		accountToWithdrawFrom.get().withdraw(amount);

		return true;
	}

	private boolean isCardValid(Card card, int pin) {
		return cardExists(card) && !card.isBlocked() && !card.isExpired() && card.checkPin(pin);
	}

	private boolean cardExists(Card cardToCheck) {
		return Arrays.stream(cards)
				.anyMatch(card -> card.equals(cardToCheck));
	}

	private boolean allowedPayTo(String shopURL) {
		return Arrays.stream(externalDomainsService.getBannedTLDs())
				.noneMatch(domain -> externalDomainsService.urlMatchesTLD(shopURL, domain));
		//extract regex
	}

	private Optional<Account> findАccount(String currency, double minimumBalance) {
		return Arrays.stream(accounts)
				.filter(acc -> acc.getCurrency().equals(currency))
				.filter(acc -> acc.hasEnoughtMoney(minimumBalance))
				.findFirst();
	}
	
	private boolean accountExists(Account account) {
		return Arrays.stream(accounts)
				.anyMatch(acc -> acc.equals(account));
	}

}
