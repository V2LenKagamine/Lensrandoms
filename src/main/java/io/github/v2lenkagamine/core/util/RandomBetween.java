package io.github.v2lenkagamine.core.util;

public class RandomBetween {
	
	public static int randBetween(int min, int max) {
		
		double rand = Math.random();
		int result = (int) Math.round(rand*(max-min) + min);
		
		return result;
		
	}
}
