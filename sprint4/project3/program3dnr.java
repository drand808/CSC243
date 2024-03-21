/*
Author:        Dominic Rando
Major:         Computer Science
Creation Date: February 20, 2024
Due Date:      February 28, 2024
Course:        CSC 243
Professor:     Dr. DeMarco
Assignment:    Program 2
Filename:      program1dnr.java
Purpose:       Takes in the users monthly income and costs, then goes through every month
               letting them make changes as needed. Then, the program will
               display the values for the entire year
*/

/*
Estimate time: 300 minutes (5 hours)
Actual time: 
*/

import java.util.Scanner;

public class program3dnr {
  public static void main(String[] args) { 
    MonthBudget january = new MonthBudget();
    MonthBudget february = new MonthBudget(1);
    System.out.println(january.getMonth());
    System.out.println(february.getMonth());
  }
}