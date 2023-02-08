package com.fmi.oop_ii.services.impl;

import com.fmi.oop_ii.models.account.Account;
import com.fmi.oop_ii.models.content.Streamable;
import com.fmi.oop_ii.models.content.enumerations.PgRating;

public class AgeRestrictionService {
	public static boolean isUserPermittedToWatch(Account user, Streamable content) {
		PgRating rating = content.getRating();
		int requiredAge = rating.getRequiredAge();
		
		//todo extract into boolean
		boolean ageRequirementMet = user.getAge() >= requiredAge;
		
		return ageRequirementMet;
	}
}