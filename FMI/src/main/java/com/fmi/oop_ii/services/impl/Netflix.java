package com.fmi.oop_ii.services.impl;

import com.fmi.oop_ii.exceptions.ContentUnavailableException;
import com.fmi.oop_ii.models.account.Account;
import com.fmi.oop_ii.models.content.Streamable;
import com.fmi.oop_ii.repositories.AccountRepository;
import com.fmi.oop_ii.repositories.VideoContentRepository;
import com.fmi.oop_ii.services.StreamingService;

public class Netflix implements StreamingService {
	private VideoContentRepository videoContentRepository;
	private AccountRepository accountRepository;

	public Netflix(Account[] accounts, Streamable[] streamableContent) {
		this.accountRepository = new AccountRepository(accounts);
		this.videoContentRepository = new VideoContentRepository(streamableContent);
	}

	@Override
	public void watch(String username, String videoContentName) throws ContentUnavailableException {
		Account userAccount = accountRepository.findByUsername(username);
		Streamable content = videoContentRepository.findByTitle(videoContentName);
		
		if (!AgeRestrictionService.isUserPermittedToWatch(userAccount , content)) {
			throw new ContentUnavailableException(String.format("User %s cannot watch %s. Pg rating age requrement is %d. User age is %d.", 
					userAccount .getUsername(), content.getTitle(), content.getRating().getRequiredAge(), userAccount .getAge()));
		}
		
		content.watch();
	}

	@Override
	public Streamable findByName(String videoContentName) {
		return videoContentRepository.findByTitle(videoContentName);	
	}

	@Override
	public Streamable mostViewed() {
		return ContentStatisticsService.getMostWatchedContent();
	}

	@Override
	public int totalWatchedTimeByUsers() {
		return ContentStatisticsService.getTotalWatchTime();
	}

}
