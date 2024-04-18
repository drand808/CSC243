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
public class Budget {
    
    int num = 12; //number of expernses in month
    MonthItem [] month = new MonthItem[num];

//default constructor
  public Budget (){
    for (int i = 0; i < num; i++) {
      month[i] =new MonthItem();
    }
    month[0].setMonth("January   ");
    month[1].setMonth("February  ");
    month[2].setMonth("March     ");
    month[3].setMonth("April     ");
    month[4].setMonth("May       ");
    month[5].setMonth("June      ");
    month[6].setMonth("July      ");
    month[7].setMonth("August    ");
    month[8].setMonth("September ");
    month[9].setMonth("October   ");
    month[10].setMonth("November ");
    month[11].setMonth("December "); 
  }

//Print initial budget

//Months
  public void printMonths() {
    System.out.printf("%-8s", "Month ");
    for (int i = 0; i < num; i++) {
	System.out.format("%-10s",month[i].getMonth());
    }
    System.out.println();
  }

//income
  public void printIncome() {
    System.out.printf("%-8s", "Income ");
    for (int i = 0; i < num; i++) {
	System.out.format("$" + "%-9.0f",month[i].getIncome());
    }	
    System.out.println();
  }

//expenses
  public void printExpenses() {
    for (int i = 0; i < 6; i++) {
        for (int j = 0; j<num; j++) {
	  if ( j == 0) {
            System.out.format("%-8s", month[j].getExpenseName(i));
	  }          
	  System.out.format("$" + "%-9.0f",month[j].getExpenseAmount(i));
        }
	  System.out.println();
    }
  }

//total
  public void printTotal() {
    System.out.printf("%-8s", "TOTAL ");
    for (int i = 0; i < num; i++) {
	System.out.format("$" + "%-9.0f",month[i].getTotal());
    }
    System.out.println();
  }

} // end or class
