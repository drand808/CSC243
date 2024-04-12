/*
Author:        Dominic Rando
Major:         Computer Science
Creation Date: April 11, 2024
Due Date:      April 10, 2024
Course:        CSC 243
Professor:     Dr. DeMarco
Assignment:    Program 4
Filename:      Expense.java
Purpose:       Class for storing information about an expense
*/

public class Expense {
  
  // Initial variables
  private String name = "UNASSIGNED";
  private double cost = 0;
  
  // Default constructor
  public Expense(){
  }
  
  /*
  Function Name:	Expense
  Description:	  Constructor for class when given data
  Parameters:     name: name of this expense
                  cost: value for this expense
  Return Value:	  None
  */
  public Expense(String name, double cost){
    this.name = name;
    this.cost = cost;
  }
  
  // Getters
  public String getName(){
    return name;
  }
  
  public double getCost(){
    return cost;
  }
  
  // Setters
  public void setName(String name){
    this.name = name;
  }
  
  public void setCost(double cost){
    this.cost = cost;
  }
}