/*
Author:        Dominic Rando
Major:         Computer Science
Creation Date: April 11, 2024
Due Date:      April 10, 2024
Course:        CSC 243
Professor:     Dr. DeMarco
Assignment:    Program 4
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
  
  public void printMonths(int currMonth){
    String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", 
                          "Aug", "Sep", "Oct", "Nov", "Dec"};
    String[] categoryNames = {"Salary", "Rent", "Car", "Gas", "Food",
                              "Savings", "Fun", "Total"};
    System.out.println("\nTable for the Year");
    System.out.println("------------------");
    System.out.print("Cat.\t");
    
    // Print month names
    for(int month = 0; month < currMonth; month++){
      System.out.printf("%-11s", monthNames[month]);
    }
    System.out.println("");
    
    // Print data
    for(int category = 0; category < categoryNames.length; category++){
      System.out.print(categoryNames[category] + "\t");
      for(int month = 0; month < currMonth; month++){
        double data = getMonth(month).getAllValues()[category];
        double value = data;
        printCurrency(value);
      }
      System.out.println("");
    }
  }
  
  // helper method
  protected void printCurrency(double value){
    String message = String.format("%s%-10.2f", "$", value); // limit to 9 chars
    System.out.print(message);
  }
}