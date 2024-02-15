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

public class shape {
  public static void main(String[] args) { 
  
    // Create a Scanner
    Scanner input = new Scanner(System.in);

    // Get input numbers
    double[] inputs = getInputDouble2(input, 
      "Enter the width and height of a rectangle: ");
      
    // Assign input numbers
    double width = inputs[0];
    double height = inputs[1];
    
    // Calculate results
    double perimeter = (2*width) + (2*height);
    double area = width*height;
    double lengthOfDiagonal = Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
    
    // Display results
    printResults(perimeter, area, lengthOfDiagonal);
  }
  
  private static void printSize(double area){
    int areaModified = int(area/100);
    switch(areaModified) {
      case 1: 
        System.out.println("The size is small");
      case 2:
        System.out.println("The size is medium");
      case 3:
        System.out.println("The size is large");
      case 4:
        System.out.println("The size is XLarge");
      case 5:
        System.out.println("The size is HUGE");
      default:
        System.out.println("Rectangle is too small!");
    }
    
    
  }
  
  private static void printResults(double perimeter, double area, double, lengthOfDiagonal){
    // Display results
    System.out.println("Perimeter: \t" + perimeter);
    System.out.println("Area: \t" + area);
    System.out.println("Diagonal: \t" + lengthOfDiagonal);
    
    
  }
  
  /*
  Function Name:	getIntInput
  Description:	  Prompts user for an int until it is supplied
  Parameters:     input: allows program to grab values from cmd line - input/export
                  prompt: message displayed to user - input
  Return Value:	  int - number user inputted
  */
  private static double[] getInputDouble2(Scanner input, String prompt){
    double width = -1;
    double height = -1;
    // Loop until valid input
    do {
      System.out.print(prompt);
      width = getNextInputDouble(input);
      height = getNextInputDouble(input);
    }
    while (!validateInputDouble(width, height, 0));
    return new double[]{width, height};
  }
  
  /*
  Function Name:	promptForInt
  Description:	  Prompts user for an value and tests if it is an int
  Parameters:     input: allows program to grab values from cmd line - input/export
                  prompt: message displayed to user - input
  Return Value:	  int - number user inputted or -1 if non-int supplied
  */
  private static double getNextInputDouble(Scanner input){
    // Test if input is an integer
    try {
      return input.nextDouble();
    }
    catch (Exception e){
      System.out.println(" > ERROR: Please make sure you are entering a number");
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
  private static boolean validateInputDouble(double inputNum1, double inputNum2, double constraint){
    if (inputNum1 > constraint && inputNum2 > constraint){
      return true;
    }
    System.out.println(" > ERROR: Please enter a number greater than " + constraint);
    return false;
  }
}