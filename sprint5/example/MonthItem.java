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

// name of class. Note: needs to match name of file
public class MonthItem {
  private String month = "Jan";
  private double income = 0;
  private int num = 6; //number of expernses in month
  private ExpenseItem [] expense = new ExpenseItem[num];
  private double expenseTotal = 0;
  private double monthTotal = 0;

//default constructor
  public MonthItem (){
    month = "Jan";
    income = 1400;
    for (int i = 0; i < num; i++) {
      expense[i] = new ExpenseItem();
    }
    expense[0].setExpense("Rent",600);
    expense[1].setExpense("Car",250);
    expense[2].setExpense("Gas",60);
    expense[3].setExpense("Food",200);
    expense[4].setExpense("Savings",58);
    expense[5].setExpense("Fun",232);
    monthTotal = calculateExpenseTotal();
  }

//set income
  public void setIncome(double amount) {
    this.income = income;
}

//set expenseAmount
  public void setAmount(int exp, double amount) {
    expense[exp].setAmount(amount);
  }

//set calculateExpenseTotal
  public double calculateExpenseTotal() {
     for (int i = 0; i < num; i++) {
	expenseTotal = expenseTotal + expense[i].getAmount();
     }
     return expenseTotal;
  }

//set month
  public void setMonth(String name) {
    this.month = name;
  }

//get month
  public String getMonth() {
    return month;
  }

//get income
  public double getIncome() {
    return income;
  }

//get expenses
  public ExpenseItem[] getExpenses() {
    return expense;
  }

//get expenseAmount
  public double getExpenseAmount(int exp) {
    return expense[exp].getAmount();
  }

//get expenseName
  public String getExpenseName(int exp) {
    return expense[exp].getName();
  }

//get Total
  public double getTotal() {
    return calculateExpenseTotal();
  }


} // end or class
