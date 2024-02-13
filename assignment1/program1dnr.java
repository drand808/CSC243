/*
Author:        Dominic Rando
Major:         Computer Science
Creation Date: February 13, 2024
Due Date:      February 14, 2024
Course:        CSC 243
Professor:     Dr. DeMarco
Assignment:    Program 1
Filename:      program1dnr.java
Purpose:       Takes the number of years until retirement and the money saved annually
               and outputs the money they will have at retirement with compound interest
*/

import java.util.Scanner;

public class program1dnr {
  public static void main(String[] args) { 
    // Constant interest rate as a percentage
    final double INTEREST_RATE = 1.05;
  
    // Create a Scanner
    Scanner input = new Scanner(System.in);

    // Enter number of years until retirement
    int years = getIntInput(input, 
      "Enter number of years until retirement as an integer: ");
    
    // Enter amount of money saved annually
    int money = getIntInput(input, 
      "Enter amount of money as an integer: ");
      
    // Calculate retirement money
    double moneyRetirement = money * (Math.pow(INTEREST_RATE, years));
    
    // Round money to two decimal places
    double moneyRetirementRounded = Math.round(moneyRetirement * 100.0)/100.0;
    
    // Display results
    System.out.println("Money at retirement: $" + moneyRetirementRounded);
  }
  
  /*
  Function Name:	getIntInput
  Description:	  Prompts user for an int until it is supplied
  Parameters:     input: allows program to grab values from cmd line - input/export
                  prompt: message displayed to user - input
  Return Value:	  int - number user inputted
  */
  private static int getIntInput(Scanner input, String prompt){
    int num;
    // Loop until valid input
    do {
      num = promptForInt(input, prompt);
    }
    while (!validateIntegerInput(num, 0));
    return num;
  }
  
  /*
  Function Name:	promptForInt
  Description:	  Prompts user for an value and tests if it is an int
  Parameters:     input: allows program to grab values from cmd line - input/export
                  prompt: message displayed to user - input
  Return Value:	  int - number user inputted or -1 if non-int supplied
  */
  private static int promptForInt(Scanner input, String prompt){
    System.out.print(prompt);
    // Test if input is an integer
    try {
      return input.nextInt();
    }
    catch (Exception e){
      System.out.println(" > ERROR: Please make sure you are entering an integer");
      input.nextLine(); // Removes input from the line, assume one-line input
      return -1;
    }
  }
  
  /*
  Function Name:	validateIntegerInput
  Description:	  Tests user input is greater than an int constraint
  Parameters:     inputNum: user input int - input
                  constraint: number input must be greater than - input
  Return Value:	  boolean - true is constraint satisfied, false otherwise
  */
  private static boolean validateIntegerInput(int inputNum, int constraint){
    if (inputNum > constraint){
      return true;
    }
    System.out.println(" > ERROR: Please enter an integer greater than " + constraint);
    return false;
  }
}