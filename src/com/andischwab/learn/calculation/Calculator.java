package com.andischwab.learn.calculation;

public class Calculator {

	public static int calculate(int operand1, int operand2, String op) {
		switch(op) {
		case "+": return Math.addExact(operand1,operand2);
		case "-": return Math.addExact(operand1,operand2 * (-1));
		case "*": return Math.multiplyExact(operand1, operand2);
		case "/": return operand1 / operand2;
		default: 
			throw new IllegalArgumentException("operation " + op + " is not implemented.");
		}
	}
}
