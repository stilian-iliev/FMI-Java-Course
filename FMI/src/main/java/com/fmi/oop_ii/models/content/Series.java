package com.fmi.oop_ii.models.content;

import com.fmi.oop_ii.models.content.enumerations.Genre;
import com.fmi.oop_ii.models.content.enumerations.PgRating;

import java.util.Arrays;

public class Series extends VideoContent {
	private final Episode[] episodes;

	public Series(String title, Genre genre, PgRating rating, Episode[] episodes) {
		//extract duration calculation into method and call it in the constructor call
		super(title, calculateDuration(episodes), rating, genre);
		this.episodes = episodes;
	}
	
	private static int calculateDuration(Episode[] episodes) {
		return Arrays.stream(episodes)
				.mapToInt(e -> e.getDuration())
				.sum();
	}
	
	public Episode[] getEpisodes() {
		return episodes;
	}

}
