// Name: Donna DeMarco
// Major: CompSci
// Creation Date: Feb 8, 2024
// Due Date: Feb 14, 2024
// Course: CSC243-20
// Professor: DeMarco
// Assignment: Program 3
// Filename: ExpenseItem.java
// Purpose: budget using classes

//Libraries needed for this program
import java.lang.String;

// name of class. Note: needs to match name of file
public class ExpenseItem {
  private String name = "none";
  private double amount = 0;

//default constructor
  public ExpenseItem (){
  }

//set expense
  public void setExpense(String name, double amount) {
    this.name = name;
    this.amount = amount;
  }

//set amount
  public void setAmount(double amount) {
    this.amount = amount;
  }

//get expense
  public double getAmount() {
    return this.amount;
  }

//get expense name
  public String getName() {
    return this.name;
  }

} // end or class

