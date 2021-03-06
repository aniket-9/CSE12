/**
* Name: Ji Woon Chung
* Class: CSE 12, Winter 15
* Date: Janurary 8, 2015
* login: cs12xbo
*
* Assignment One
* File Name: hw1.java
* Description: This program test functions to display output strings and 
*	 numbers. 
*
* The hw1 class is a direct port of hw1.c to java.
* As you already know in java when you pass literal strings like
* <P>
*   writeline("a literal string\n", stream);
* <P>
* in C is considered a char[], but in java it's automatically
* converted and treated as a String object.  Therefore 
* the function writeline accepts literal strings and 
* String types.
*/

import java.io.*;      // System.out

/* Class name: hw1
* Purpose: Test functions to display output strings and numbers. 
* Parameters: Nothing.
* Return: Nothing.
*/
public class hw1 {
  private static final int COUNT = 8;	// number of hex digits to display
  private static final int DECIMAL = 10;	// to indicate base 10
  private static final int HEX = 16;		// to indicate base 16
  private static final char digits[] = 	// used for ASCII conversion
  new String("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
  private static int hexCounter = 0; // counter for the number hex digits
  private static int SEVEN = 7; //so that the array will have seven empty 
	//spaces

  /**
  * Function Name: baseout
  * Purpose:       Takes in a positive number and displays in a given base.
  * Description:    Continually modes the number with the base, that's passed
  *   through, so that a single digit will be created, which then will be stored
	*   into the character array and the number is also continually divided by the
	*   base creating a new number until the do while condition is 
	*		met. Then the single digit stored in the character array is printed out.
	*		Since, each character is stored into the character array backwards, 
	*		I decremented the array so that the characters will print out the correct
	*		way. 
	* Parameters: 
  *   int number:         Numeric value to be displayed.
  *   int base:           Base to used to display number.
  *   PrintStream stream: Where to display, likely System.out or System.err.
	* Return: Nothing
  */
  private static void baseout (int number, int base, PrintStream stream) {
    int index = 0; //initialized index to zero.
    int remainder = 0; //initialized remainder to zero.
    char[] array = new char[COUNT]; //initializing the character array to have 
		//eight spaces.
    //If the base is a HEX, the array will start off by having
		//zeros.
		if(base == HEX) { 
      array[SEVEN] = digits[hexCounter];
    }
		//Will run this while loop at least once. 
    do {
      remainder = number % base; //Remainder = the single digit, created by 
			//moding number with the base. 
      array[index] = digits[remainder]; //Storing that single digit character
			//into the character array replacing the zeros if base equals hex.
      number = number / base; //A new number will be created by dividing the 
			//old number by the base. 
      index++; //Increase the index storing the single digit character
			//into a new number.
    } while (number > 0); //A while loop that runs the function continually if
		//the number is bigger than zero.
    while (index >= 0) { //A while loop that runs the function if the index 
			//is bigger or equal to zero. 
      fputc(array[index], stream); //Prints out the single digit characters
      index--; //decrement the index. 
    }
  }

  /**
  * Function Name: decout
  * Purpose: Takes in a positive number and displays it in decimal.
  * Description: Calls the baseout function with new parameters being passed
	*   to it. 
	* Parameters: 
  *   int number: positive numeric value to be displayed.
  *   PrintStream stream: Where to display, likely System.out or System.err.
	* Return: Void.
  */
  public static void decout (int number, PrintStream stream) { 
    baseout(number, DECIMAL, stream); //Calls the baseout function 
		//placing a number and a base of decimal.
  }

  /**
  * Function name: fputc
	* Purpose: Displays a single character.
	* Parameters: 
  *   char CharToDisp: Character to display.
  *   PrintStream stream: Where to display, likely System.out or System.err.
	* Return: Void
  */
  public static void fputc(char CharToDisp, PrintStream stream) {
    // Print a single character.
    stream.print (CharToDisp);   
    // Flush the system.out buffer, now. 
    stream.flush ();
  }

  /**
  * Function name: hexout
	* Purpose: Takes in a positive number and displays it in hex.
  * Description: Goal is achieved via delegating to the baseout function.
	* Parameter:
  *   int number: A positive numeric value to be displayed in hex.
  *   PrintStream: Where to display, likely System.out or System.err.
	* Return: Void. 
  */
  public static void hexout (int number, PrintStream stream) {
    // Output "0x" for hexidecimal.
    writeline ("0x", stream);
    baseout (number, HEX, stream);
  }

  /**
  * Function name: newline
	* Purpose: Prints out a newline character.
  * Description: A new line character is created. 
	* Parameter:
	*   PrintStream stream: Where to display, likely System.out or System.err.
  */
  public static void newline ( PrintStream stream ) {
    //prints out a new line
		fputc('\n', stream);
  }

  /**
  * Function Name: writeline
	* Purpose: Prints out a string.
  * Description: Each character of the message/string is stored into an array
	*							 and increments until the function hits the NULL character.
  * Parameters:	
	*		String message:  A string to print out.
  *		PrintStream stream: Where to display, likely System.out or System.err.
  * Return: <code>int</code> The length of the string.
  */
  public static int writeline (String message, PrintStream stream) {     
    //A for loop that stores each character into an array if the message 
		//length is bigger than the index. index is initialized to zero and 
		//increments until it the for loop condition is met. 
		for(int index = 0; index < message.length(); index++) {
      //Prints out each character of the index.
			fputc(message.charAt(index), stream);
    }
    return 0;
  }

  public static void main( String[] args ) {
    System.err.print("Zdravei Sviat\n");
    writeline ("Hello World", System.out);
    newline(System.out);
    decout (123, System.out);
    newline(System.out);
    decout (0, System.out);
    newline(System.out);
    hexout (0xFEEDDAD, System.out);
    newline(System.out);
  }
}
