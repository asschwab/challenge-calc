package com.andischwab.learn.calculation;

public class CalculationChallengeGenerator {
	
	public static final int DEFAULT_NUMBER_CHALLENGES = 1000;
	public static final int DEFAULT_MAX_NUMBER = 10;
	
	

	public static void main (String[] args) {
		
		int numberOfChallenges = DEFAULT_NUMBER_CHALLENGES;
		int maxNumber = DEFAULT_MAX_NUMBER;
				
		for(int i = 1; i < numberOfChallenges; i++) {
			System.out.println(CalcChallenge.generateChallenge(maxNumber, null));
		}
	}
}
