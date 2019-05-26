import java.util.Scanner;

/**
 * @author Ryan Hayward
 *
 * This is a Java 8 program designed according to the specification of the CodeForces Algorithm Challenge: http://codeforces.com/contest/962/problem/C
 * 
 * This program uses bitmasking to loop through all possible setups of the input string and finds the largest number contained within the string which satisfies these prerequisites:
 *   - The square root of this number must be an integer
 *   - The number must not have any leading zeroes
 */

public class MakeASquare
{
	public static void main(String [] args)
	{
		System.out.println("Please enter a string of integers under 16 digits long");
		
		// TODO add validation
		
		Scanner s = new Scanner(System.in);
		
		StringBuilder input = new StringBuilder(s.nextLine());
		
		s.close();
		
		/**
		 * Starts timer
		 */
		long startTime = System.currentTimeMillis();

		long inputSize = input.length();
		
		StringBuilder outputSquare = new StringBuilder("");
		
		long largestLength = 0;
				
		/**
		 * Given an input size of 4, it takes 1 and moves it 4 places to the left (10000), which is 16, and takes away 1, giving 15 (1111),
		 * creating the original bitmask required for this string. 
		 * 
		 * It then iterates through all possible setups of this binary number by taking away 1 until it reaches 0 (0000)
		 * Example: loop 1: 1111, 2: 1110, 3: 1101, 4: 1100, etc.
		 */
		for (long mask = (1 << inputSize) - 1; mask > 0; mask--)
		{
			StringBuilder potentialSquare = new StringBuilder("");
			long length = 0;
			
			for (int i = 0; i < inputSize; i++)
			{
				/**
				 * This takes the mask e.g. 1001 and shifts it right by the size minus one minus the current digit to check
				 * for example, given an input 8314 on the first loop it will use bitwise operations to shift it to become just 1
				 * it sees if this AND 1 is true, if so that means that the mask is displaying a 1 for that place value.
				 * This then appends the character to the final string.
				 * 
				 * On the second loop of the mask 1001, it will shift it to become 100,
				 * it will then see if this AND 1 is true, which is false because (0b100 & 0b001) is 0.
				 * 
				 * It goes on until all values of this string have been checked.
				 */
				if (((mask >> inputSize - 1 - i) & 1) == 1) 
				{
					potentialSquare.append(input.charAt(i));
					length += 1;
				}
			}
			/**
			 * Checks if square root is int
			 */
			if (java.lang.Math.floor(java.lang.Math.sqrt(Long.parseLong(potentialSquare.toString()))) == java.lang.Math.sqrt(Long.parseLong(potentialSquare.toString())))
			{
				/**
				 * Checks if no leading zeroes
				 */
				if (!((Character) potentialSquare.charAt(0)).equals('0'))
				{
					/**
					 * Checks if the length is biggest of all potential int squares
					 * 
					 */
					if (length > largestLength)
					{
						largestLength = length;
						outputSquare = potentialSquare;
					}
				}
			}
		}
		
		/** 
		 * If it reaches the end and no possible integer sqrts have been found
		 */
		if (largestLength == 0)
		{
			System.out.println("That number contains no possible integer square roots.");
		}
		else
		{
			System.out.println("FINAL NUMBER:");
			System.out.println(outputSquare + " (" + Integer.toString((int)java.lang.Math.sqrt(Integer.parseInt(outputSquare.toString())))  + "^2)" + "\n");
			
			System.out.println("LOWEST AMOUNT OF STEPS");
			System.out.println(inputSize - largestLength);
		}
		
		/**
		 * Ends timer and calculates time taken to run program
		 */
		long endTime = System.currentTimeMillis();
		System.out.println("Took "+ (endTime - startTime) + "ms");
	}
}