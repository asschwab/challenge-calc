package com.andischwab.learn.calculation;

/**
 * Generates simple arithmetic problems for add, subtract, multiply, divide for pupils to solve.
 * 
 * @author Andi Schwab
 *
 */
public class CalculationChallengeGenerator {

	// how many calculation challenges should be generated (arg 1)
	public static final int DEFAULT_NUMBER_CHALLENGES = 100;
	// how large may the operands be (arg 2)
	public static final int DEFAULT_MAX_OPERAND = 9;
	// how large may the result be (arg 3)
	public static final int DEFAULT_MAX_RESULT = -1; // means no limit
	// should the results of calculation also be printed (teach mode) (arg 4)
	public static final boolean WITH_RESULTS = false;
	// should zero be included as operand (arg 5)
	private static final boolean DEFAULT_INCLUDE_ZERO = false;
	// should negative numbers be included as operand (arg 6)
	private static final boolean DEFAULT_INCLUDE_NEGATIVES = false;

	public static void main(String[] args) {

		int numberOfChallenges = DEFAULT_NUMBER_CHALLENGES;
		int maxOperand = DEFAULT_MAX_OPERAND;
		int maxResult = DEFAULT_MAX_RESULT;
		boolean withResults = WITH_RESULTS;
		boolean includeZero = DEFAULT_INCLUDE_ZERO;
		boolean includeNegativeNumbers = DEFAULT_INCLUDE_NEGATIVES;
		String theOneOp = null;

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
			// should we use only one specific operation out of ("+", "-", "*", "/")?
			if (args.length > 6) {
				theOneOp = args[6];
				if (!CalcChallenge.isKnownOp(theOneOp)) {
					throw new IllegalArgumentException(theOneOp + " is not supported.");
				}
			}

		}

		for (int i = 1; i <= numberOfChallenges; i++) {
			System.out.println(CalcChallenge.generateChallenge(maxOperand, maxResult, withResults, theOneOp,
					includeZero, includeNegativeNumbers));
		}
	}
}
