package com.fmi.oop_ii.models.content;

import com.fmi.oop_ii.models.content.enumerations.Genre;
import com.fmi.oop_ii.models.content.enumerations.PgRating;
import com.fmi.oop_ii.services.impl.ContentStatisticsService;

public abstract class VideoContent implements Streamable {
	private final String title;
	private final int duration;
	private int timesWatched;
	private final PgRating pgRating;
	private final Genre genre;

	public VideoContent(String title, int duration, PgRating pgRating, Genre genre) {
		this.title = title;
		this.duration = duration;
		this.pgRating = pgRating;
		this.genre = genre;
		this.timesWatched = 0;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public int getDuration() {
		return duration;
	}

	@Override
	public PgRating getRating() {
		return pgRating;
	}
	
	public Genre getGenre() {
		return genre;
	}
	
	public void watch() {
		timesWatched++;
		ContentStatisticsService.processContentWatching(this);
	}
	
	public int getTimesWatched() {
		return timesWatched;
	}

}