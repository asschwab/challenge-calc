package com.andischwab.learn.calculation;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CalcChallenge {

	private final int firstNumber;
	
	private final int secondNumber;
	
	private final String operator;
	
	private static final Random randomizer = new Random();
	
	public static final String[] operators =  {"+", "-", "*", "/"};
	
	private static Set<CalcChallenge> drawn = new HashSet<>();
	
	private CalcChallenge(int first, int second, String op) {
		this.firstNumber = first;
		this.secondNumber = second;
		this.operator = op;
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
		return firstNumber + " " + operator + " " + secondNumber + " =            ";
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

	public static CalcChallenge generateChallenge(int maxNumber, String op) {
		if (op == null) {
			op = drawOperator();
		}
		int maxNumberToBeUsed = maxNumber + 1;		
		CalcChallenge cc = createCalcChallenge(randomizer.nextInt(maxNumberToBeUsed),randomizer.nextInt(maxNumberToBeUsed),op);
		int j = 0;
		while (cc == null || drawn.contains(cc)) {
			cc = createCalcChallenge(randomizer.nextInt(maxNumberToBeUsed),randomizer.nextInt(maxNumberToBeUsed),op);
			j++;
			if (j > 100 && cc != null) {
				// allow creating same again
				return cc;
			}
		}
		drawn.add(cc);
		return cc;
	}

	private static CalcChallenge createCalcChallenge(int first, int second, String op) {
		switch(op) {
		case "-": {
			if (second > first) {
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
		return new CalcChallenge(first, second, op);
	}

	private static String drawOperator() {
		String drawnOp = operators[randomizer.nextInt(operators.length)];
		//System.out.println("drawn operator: "  + drawnOp);
		return drawnOp;
	}
	
	
}
