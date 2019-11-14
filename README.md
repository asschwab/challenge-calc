# challenge-calc
## Summary
Generates simple arithmetic problems for add, subtract, multiply, divide for pupils to solve.

## Result example
```
2 / 2                               =                  
2 + 4                               =                  
4 * 4                               =                  
9 - 3                               =                  
4 * 1                               =                  
9 * 3                               =                  
6 * 6                               =                  
9 * 9                               =                  
6 - 3                               =                  
8 - 8                               =                  
9 + 5                               =                  
5 * 5                               =                  
8 * 4                               =                  
10 * 10                             =                  
8 * 4                               =                  
8 + 3                               =                  
10 * 5                              =                  
10 + 3                              =                  
2 - 2                               =                  
2 / 2                               =                  
4 / 4                               =                  
7 * 7                               =                  
9 - 1                               =                  
```

## Program args

```java
  // how many calculation challenges should be generated (arg 1)
	public static final int DEFAULT_NUMBER_CHALLENGES = 100;
  // how large may the operaands be (arg 2)
	public static final int DEFAULT_MAX_OPERAND = 9;
  // how large may the result be  (arg 3)
	public static final int DEFAULT_MAX_RESULT = -1; // means no limit
  // should the results of calculation also be printed (teach mode) (arg 4)
	public static final boolean WITH_RESULTS = false;
  // should zero be included as operand (arg 5)
	private static final boolean DEFAULT_INCLUDE_ZERO = false;
  // should negative numbers be included as operand (arg 6)
	private static final boolean DEFAULT_INCLUDE_NEGATIVES = false;
		
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
      // should we use only one specific operation out of ("+", "-", "*", "/")?
			if (args.length > 6) {
				theOneOperand = args[6];
				if (!CalcChallenge.isKnownOp(theOneOperand)) {
					throw new IllegalArgumentException(theOneOperand + " is not supported.");
				}
			}
```
