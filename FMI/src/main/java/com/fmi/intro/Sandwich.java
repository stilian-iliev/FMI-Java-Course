package com.fmi.intro;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Sandwich {
	private static final String BREAD = "bread";
	private static final String DISLIKED_INGREDIENT = "olives";
	private static final String INGREDIENT_SPLITTER = "-";

	public static void main(String[] args) {
		System.out.println(Arrays.stream(extractIngredients("asdbreadham-tomato-mayobreadblabla"))
				.collect(Collectors.joining(", ")));
		System.out.println(Arrays.stream(extractIngredients("asdbreadham-olives-tomato-olives-mayobreadblabla"))
				.collect(Collectors.joining(", ")));
		System.out.println(Arrays.stream(extractIngredients("asdbreadham")).collect(Collectors.joining(", ")));

	}

	public static String[] extractIngredients(String sandwich) {
		int indexOfFirstBread = sandwich.indexOf(BREAD);
		int indexOfLastBread = sandwich.lastIndexOf(BREAD);

		if (indexOfFirstBread == indexOfLastBread) {
			return new String[0];
		}

		String ingredientsString = sandwich
				.substring(indexOfFirstBread + BREAD.length(), indexOfLastBread);
		
		String[] ingredients = ingredientsString.split(INGREDIENT_SPLITTER);

		return Arrays.stream(ingredients)
				.filter(ing -> !ing.equals(DISLIKED_INGREDIENT))
				.sorted()
				.toArray(String[]::new);
	}

}
