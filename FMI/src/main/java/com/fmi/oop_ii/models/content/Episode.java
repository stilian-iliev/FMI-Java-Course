package com.fmi.oop_ii.models.content;

public class Episode {
	private String name;
	private int duration;

	Episode(String name, int duration) {
		this.name = name;
		this.duration = duration;
	}
	
	public String getName() {
		return name;
	}
	
	public int getDuration() {
		return duration;
	}
}
