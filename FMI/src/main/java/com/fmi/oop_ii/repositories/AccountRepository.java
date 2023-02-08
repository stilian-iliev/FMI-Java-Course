package com.fmi.oop_ii.repositories;

import com.fmi.oop_ii.exceptions.UserNotFoundException;
import com.fmi.oop_ii.models.account.Account;

import java.util.Arrays;

public class AccountRepository {
	private Account[] accounts;
	
	public AccountRepository(Account[] accounts) {
		this.accounts = accounts;
	}
	
	public Account findByUsername(String username) {
		return Arrays.stream(accounts)
				.filter(acc -> acc.getUsername().equals(username))
				.findFirst()
				.orElseThrow(() -> new UserNotFoundException(String.format("User %s not found.", username)));
	}

}
