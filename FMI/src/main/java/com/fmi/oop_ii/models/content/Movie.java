package com.fmi.oop_ii.models.content;

import com.fmi.oop_ii.models.content.enumerations.Genre;
import com.fmi.oop_ii.models.content.enumerations.PgRating;

public class Movie extends VideoContent {

	public Movie(String title, Genre genre, PgRating rating, int duration) {
		super(title, duration, rating, genre);
	}

}
