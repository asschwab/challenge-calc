package com.andischwab.learn.calculation;

public class CalculationChallengeGenerator {
	
	public static final int DEFAULT_NUMBER_CHALLENGES = 100;
	public static final int DEFAULT_MAX_OPERAND = 9;
	public static final int DEFAULT_MAX_RESULT = -1;
	public static final boolean WITH_RESULTS = false;
	private static final boolean DEFAULT_INCLUDE_ZERO = false;
	private static final boolean DEFAULT_INCLUDE_NEGATIVES = false;
	

	public static void main (String[] args) {
		
		int numberOfChallenges = DEFAULT_NUMBER_CHALLENGES;
		int maxOperand = DEFAULT_MAX_OPERAND;
		int maxResult = DEFAULT_MAX_RESULT;
		boolean withResults = WITH_RESULTS;
		String theOneOperand = null;
		boolean includeZero = DEFAULT_INCLUDE_ZERO;
		boolean includeNegativeNumbers = DEFAULT_INCLUDE_NEGATIVES;
		
		if (args.length > 0) {
			if (args.length >= 1) {
				numberOfChallenges = Integer.parseInt(args[0]);
			}
			if (args.length > 1) {
				maxOperand = Integer.parseInt(args[1]);
			}
			if (args.length > 2) {
				maxResult = Integer.parseInt(args[2]);
			}
			if (args.length > 3) {
				withResults = Boolean.parseBoolean(args[3]);
			}
			if (args.length > 4) {
				includeZero = Boolean.parseBoolean(args[4]);
			}
			if (args.length > 5) {
				includeNegativeNumbers = Boolean.parseBoolean(args[5]);
			}
			if (args.length > 6) {
				theOneOperand = args[6];
				if (!CalcChallenge.isKnownOp(theOneOperand)) {
					throw new IllegalArgumentException(theOneOperand + " is not supported.");
				}
			}

		}
				
		for(int i = 1; i <= numberOfChallenges; i++) {
			System.out.println(
					CalcChallenge.generateChallenge(
					maxOperand, maxResult, withResults, theOneOperand, includeZero, includeNegativeNumbers));
		}
	}
}
