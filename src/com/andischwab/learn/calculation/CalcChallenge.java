package com.andischwab.learn.calculation;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CalcChallenge {
	
	public static final NumberFormat nf = NumberFormat.getIntegerInstance();

	private final int firstNumber;
	
	private final int secondNumber;
	
	private final String operator;
	
	private final int resultInt;

	private final boolean resultOverflowOccured;
	
	private static final Random randomizer = new Random();
	
	public static final String[] operators =  {"+", "-", "*", "/"};

	private static final String EMPTY = " ";
	
	private static Set<CalcChallenge> drawn = new HashSet<>();
	
	private CalcChallenge(int first, int second, String op, int result, boolean resultOverflow) {
		this.firstNumber = first;
		this.secondNumber = second;
		this.operator = op;
		this.resultInt = result;
		this.resultOverflowOccured = resultOverflow;
	}

	public int getFirstNumber() {
		return firstNumber;
	}

	public int getSecondNumber() {
		return secondNumber;
	}

	public String getOperator() {
		return operator;
	}
	
	
	
	@Override
	public String toString() {
		String openBracket = "";
		String closeBracket = "";
		String resultStringToBeOutput = resultOverflowOccured ? "overflow occurred" : nf.format(resultInt);
		String result = (resultOverflowOccured || resultInt > Integer.MIN_VALUE) ? "(" + resultStringToBeOutput +")" : "";
		if (secondNumber < 0) {
			openBracket = "(";
			closeBracket = ")";
		}
		String beforeEqualsSign = nf.format(firstNumber) + " " + operator + " " + openBracket + nf.format(secondNumber) + closeBracket;
		int numberOfEmptySpaces = 35 - beforeEqualsSign.length();
		String emptySpaces = "";
		for (int i = 0; i < numberOfEmptySpaces; i++) {
			emptySpaces += EMPTY;
		}
		return beforeEqualsSign + emptySpaces + " =                  " + result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + firstNumber;
		result = prime * result + ((operator == null) ? 0 : operator.hashCode());
		result = prime * result + secondNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CalcChallenge other = (CalcChallenge) obj;
		if (firstNumber != other.firstNumber)
			return false;
		if (operator == null) {
			if (other.operator != null)
				return false;
		} else if (!operator.equals(other.operator))
			return false;
		if (secondNumber != other.secondNumber)
			return false;
		return true;
	}
	
	public static CalcChallenge generateChallenge(int maxOperand, int maxResult, boolean withResults, String opIn, boolean includeZero, boolean includeNegativeNumbers) {
		String op = null;
		if (opIn == null) {
			op = drawOperator();
		} else if (!isKnownOp(op)){
			op = drawOperator();
		} else {
			op = opIn;
			
		}
		int maxNumberToBeUsed = maxOperand + 1;		
		CalcChallenge cc = createCalcChallenge(randomizer.nextInt(maxNumberToBeUsed),randomizer.nextInt(maxNumberToBeUsed),op, maxResult, withResults, includeNegativeNumbers);
		int j = 0;
		int firstOp, secondOp, firstOpNegative, secondOpNegative;
		while (cc == null || drawn.contains(cc)) {
			firstOpNegative = (includeNegativeNumbers && randomizer.nextInt(6)  == 0) ? -1 : 1;
			firstOp = randomizer.nextInt(maxNumberToBeUsed) * firstOpNegative;		
			secondOpNegative = (includeNegativeNumbers && randomizer.nextInt(6) == 0) ? -1 : 1;
			secondOp = randomizer.nextInt(maxNumberToBeUsed) * secondOpNegative;
			if (!includeZero) {
				firstOp = Math.max(firstOp, 1);
				secondOp = Math.max(secondOp, 1);
			}
			cc = createCalcChallenge(firstOp,secondOp,op, maxResult, withResults, includeNegativeNumbers);
			j++;
			if (j > 100 && cc != null) {
				// allow creating same again
				return cc;
			}
		}
		drawn.add(cc);
		return cc;
	}

	public static boolean isKnownOp(String op) {
		return new ArrayList<String>(Arrays.asList(CalcChallenge.operators)).contains(op);
	}

	private static CalcChallenge createCalcChallenge(int first, int second, String op, int maxResult, boolean withResults, boolean includeNegativeNumbers) {
		switch(op) {
		case "-": {
			if (!includeNegativeNumbers && second > first) {
				return null;
			}
		}
		case "*" : {
			if (second == 0 || first == 0) {
				return null;
			}
		}
		case "/": {
			if (second == 0 || first == 0) {
				return null;
			}
			if (first % second != 0) {
				return null;
			}
		}		
		}
		int result = Integer.MIN_VALUE;
		boolean resultOverflow = false;
		if (maxResult > 0 || withResults) {
			try {
				int resultCalculated = Calculator.calculate(first, second, op);
				if (maxResult > 0 && resultCalculated > maxResult) {
					return null;
				}
				if (withResults) {
					result = resultCalculated;
				}
			} catch (ArithmeticException ae) {
				resultOverflow = true;
			}
			
		}

		return new CalcChallenge(first, second, op, result, resultOverflow);
	}

	private static String drawOperator() {
		String drawnOp = operators[randomizer.nextInt(operators.length)];
		//System.out.println("drawn operator: "  + drawnOp);
		return drawnOp;
	}
	
	
}
