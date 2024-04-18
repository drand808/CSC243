// Name: Donna DeMarco
// Major: CompSci
// Creation Date: Feb 8, 2024
// Due Date: Feb 14, 2024
// Course: CSC243-20
// Professor: DeMarco
// Assignment: Program 3
// Filename: MonthItem.java
// Purpose: budget using classes

//Libraries needed for this program
import java.lang.String;
import java.util.Scanner;
import java.io.*;

// name of class. Note: needs to match name of file
public class prog3DDM {
  public static void main (String[] args) {
    
    Budget current = new Budget();

//Print initial budget
//Months
    current.printMonths();
    current.printIncome();
    current.printExpenses();
    current.printTotal();
  }  // end of main
} // end or class
