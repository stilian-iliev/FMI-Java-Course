package com.fmi.oop_ii.services.impl;

import com.fmi.oop_ii.models.content.VideoContent;

//make both services singleton
public class ContentStatisticsService {
	private static int totalWatchTime;
	private static VideoContent mostWatchedContent;
	
	//order methods 
	public static void processContentWatching(VideoContent content) {
		increaseTotalWatchTime(content.getDuration());
		
		if (content.getTimesWatched() > mostWatchedContent.getTimesWatched()) {
			setMostWatchedContent(content);
		}
	}
	
	private static void increaseTotalWatchTime(int duration) {
		totalWatchTime += duration;
	}
	
	private static void setMostWatchedContent(VideoContent mostWatchedContent) {
		ContentStatisticsService.mostWatchedContent = mostWatchedContent;
	}

	public static int getTotalWatchTime() {
		return totalWatchTime;
	}
	
	public static VideoContent getMostWatchedContent() {
		return mostWatchedContent;
	}
}
