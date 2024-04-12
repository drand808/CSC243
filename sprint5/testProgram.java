/*
Author:        Dominic Rando
Major:         Computer Science
Creation Date: Arpil 11, 2024
Due Date:      April 10, 2024
Course:        CSC 243
Professor:     Dr. DeMarco
Assignment:    Program 4
Filename:      testProgram.java
Purpose:       Tests three different cases based off program4's classes.
               Each case should handle the error with exceptions
*/

import java.util.Scanner;
import java.util.InputMismatchException;

public class testProgram {
  public static void main(String[] args) {
    // Happy path
    try {
      YearBudget budget = new YearBudget();
      for(int i = 0; i < 12; i++){
        MonthBudget filler = new MonthBudget(i, 1000, 100, 100, 100, 100, 80);
        budget.setMonth(i, filler);
      }
      budget.printMonths(12);
    }
    catch (IllegalArgumentException ex) {
      System.out.println(ex);
    }
    
    // Bad input
    try{
      // accept input to make a bad month budget
      Scanner input = new Scanner(System.in);
      System.out.print("Enter monthly salary: ");
      double num = input.nextDouble();
      MonthBudget filler = new MonthBudget(0, num, 100, 100, 100, 100, 80);
    }
    catch (InputMismatchException ex){
      System.out.println(ex);
    }
    
    // Index out of bounds
    try{
      YearBudget budget = new YearBudget();
      MonthBudget filler = new MonthBudget(13, 1000, 100, 100, 100, 100, 80);
      budget.setMonth(13, filler);
    }
    catch (IndexOutOfBoundsException ex){
      System.out.println(ex);
    }
  }
}