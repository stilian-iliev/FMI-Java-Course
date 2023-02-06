package com.fmi.intro;

public class Rememberall {
	private static final char DELIMITER_DASH = '-';
	private static final char DELIMITER_SPACE = ' ';

	public static void main(String[] args) {
		System.out.println(isPhoneNumberForgettable(""));
		System.out.println(isPhoneNumberForgettable("498-123-123"));
		System.out.println(isPhoneNumberForgettable("0894 123 567"));
		System.out.println(isPhoneNumberForgettable("(888)-FLOWERS"));
		System.out.println(isPhoneNumberForgettable("(444)-greens"));
		System.out.println(isPhoneNumberForgettable("444-greens"));
	}

	public static boolean isPhoneNumberForgettable(String phoneNumber) {
		if (phoneNumber == null || phoneNumber.isEmpty()) {
			return false;
		}

		boolean[] digits = new boolean[10];
		boolean forgettable = true;

		for (int i = 0; i < phoneNumber.length(); i++) {
			char currentChar = phoneNumber.charAt(i);

			if (isDelimiter(currentChar)) {
				continue;
			}

			if (Character.isAlphabetic(currentChar)) {
				return true;
			}

			if (Character.isDigit(currentChar) && forgettable) {
				int numericValueOfCharacter = Character.getNumericValue(currentChar);
				
				if (digits[numericValueOfCharacter]) {
					forgettable = false;
				} else {
					digits[numericValueOfCharacter] = true;
				}
			}
		}

		return forgettable;
	}

	private static boolean isDelimiter(char character) {
		return (character == DELIMITER_DASH) || (character == DELIMITER_SPACE);
	}
}
