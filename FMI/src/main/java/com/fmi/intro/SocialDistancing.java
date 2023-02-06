package com.fmi.intro;

public class SocialDistancing {
	private static final int EMPTY_SEAT = 0;
	private static final int NOT_STARTED = -1;
	
	public static void main(String... args) {
		// 2,3,1,2,3,4,3,3
		System.out.println(maxDistance(new int[] { 1, 0, 0, 0, 1, 0, 1 }));
		System.out.println(maxDistance(new int[] { 1, 0, 0, 0 }));
		System.out.println(maxDistance(new int[] { 0, 1 }));
		System.out.println(maxDistance(new int[] { 1, 0, 0, 0, 0, 1 }));
		System.out.println(maxDistance(new int[] { 1, 0, 0, 0, 0, 0, 1 }));
		System.out.println(maxDistance(new int[] { 0, 0, 0, 0, 1 }));
		System.out.println(maxDistance(new int[] { 0, 0, 0, 1, 0, 0, 0, 1 }));
		System.out.println(maxDistance(new int[] { 1, 0, 0, 0, 1, 0, 0, 0 }));
	}

	public static int maxDistance2(int[] seats) {
		StringBuilder seatsAsString = new StringBuilder();

		for (int i : seats) {
			seatsAsString.append(i);
		}

		String[] freeSeatsGroups = seatsAsString.toString().split("[1]+", -1);
		int maxDistance = 0;
		// 1,0,0,0,1,0
		// ["", "000" ,"0"]

		for (int i = 0; i < freeSeatsGroups.length; i++) {
			String seatBunch = freeSeatsGroups[i];
			int currentDistance = 0;

			if (i == 0 || i == freeSeatsGroups.length - 1) {
				currentDistance = seatBunch.length();
			} else {
				currentDistance = seatBunch.length() / 2;

				if (seatBunch.length() % 2 == 1) {
					currentDistance++;
				}
			}
			if (currentDistance > maxDistance) {
				maxDistance = currentDistance;
			}
		}

		return maxDistance;
	}

	public static int maxDistance(int[] seats) {
		if (seats == null) {
			throw new IllegalArgumentException("Seats shouldn't be null.");
		}

		int maxDistance = 0;
		int freeSeatsStartIndex = NOT_STARTED;

		for (int i = 0; i < seats.length; i++) {
			if (seats[i] == EMPTY_SEAT) {
				if (freeSeatsStartIndex == NOT_STARTED) {
					freeSeatsStartIndex = i;
				}

				int currentDistance = i - freeSeatsStartIndex + 1;

				if (isNotEdgeSeat(freeSeatsStartIndex, i, seats.length)) {
					boolean isOdd = currentDistance % 2 == 1;
					currentDistance /= 2;
					
					if (isOdd) {
						currentDistance++;
					}
				}

				if (currentDistance > maxDistance) {
					maxDistance = currentDistance;
				}
			} else {
				freeSeatsStartIndex = NOT_STARTED;
			}
		}

		return maxDistance;
	}

	private static boolean isNotEdgeSeat(int start, int end, int length) {
		return start != 0 && end != (length - 1);
	}
}
