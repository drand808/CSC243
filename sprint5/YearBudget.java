/*
Author:        Dominic Rando
Major:         Computer Science
Creation Date: March 25, 2024
Due Date:      April  1, 2024
Course:        CSC 243
Professor:     Dr. DeMarco
Assignment:    Program 3
Filename:      YearBudget.java
Purpose:       Class for storing budget data for a year.
               Contains 12 MonthBudgets for the year.
*/

public class YearBudget {
  
  // Initial variables
  private MonthBudget[] months = new MonthBudget[12];
  
  // Default constructor
  public YearBudget(){
  }
  
  /*
  Function Name:	YearBudget
  Description:	  Constructor for class when given the first month
  Parameters:     firstMonth: data for the first month's budget
  Return Value:	  None
  */
  public YearBudget(MonthBudget firstMonth){
    this.months[0] = firstMonth;
  }
  
  // Getters
  public MonthBudget getMonth(int month){
    return this.months[month];
  }
  
  // Setters
  public void setMonth(int month, MonthBudget monthData){
    this.months[month] = monthData;
  }
}