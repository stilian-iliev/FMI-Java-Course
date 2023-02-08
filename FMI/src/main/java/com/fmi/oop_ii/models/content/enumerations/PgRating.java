package com.fmi.oop_ii.models.content.enumerations;

public enum PgRating {
    G("General audience", 0), // it is available for everyone
    PG13("May be inappropriate for children under 13", 14), // it is available only for people aged 14 and over
    NC17("Adults Only", 18); // it is available only for people aged 18 and over

	private final String details;
    private final int requiredAge;

    PgRating(String details, int minimumAge) {
        this.details = details;
		this.requiredAge = minimumAge;
    }
    
    public String getDetails() {
		return details;
	}
    
    public int getRequiredAge() {
		return requiredAge;
	}
}