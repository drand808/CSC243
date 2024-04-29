/*
Author:        Dominic Rando
Major:         Computer Science
Creation Date: April 11, 2024
Due Date:      April 10, 2024
Course:        CSC 243
Professor:     Dr. DeMarco
Assignment:    Program 4
Filename:      MonthBudget.java
Purpose:       Class for storing budget data for a month
*/

public class MonthBudget {
  
  // Initial variables
  private int month = 0;
  private double percentForSavings = 0;
  private double income = 0;
  private double savings = 0;
  private double fun = 0;
  private double total = 0;
  // rent, car, gas, food
  private Expense[] expenses = new Expense[4];
  
  static final String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", 
                          "Aug", "Sep", "Oct", "Nov", "Dec"};
  
  /*
  Function Name:	MonthBudget
  Description:	  Default Constructor
  Parameters:     None
  Return Value:	  None
  */
  public MonthBudget(){
  }
  
  /*
  Function Name:	MonthBudget
  Description:	  Constructor for class when given data
  Parameters:     month: number of the year for this month
                  all others: data values for class
  Return Value:	  None
  */
  public MonthBudget(int month, double income, double rent, double car, 
                      double gas, double food, double percentForSavings){
    this.month = month;
    this.percentForSavings = percentForSavings;
    this.income = income;
    expenses[0] = new Expense("Rent", rent);
    expenses[1] = new Expense("Car", car);
    expenses[2] = new Expense("Gas", gas);
    expenses[3] = new Expense("Food", food);
    calculateDisposables();
  }
  
  /*
  Function Name:	MonthBudget
  Description:	  Copy constructor for class
  Parameters:     month: number of the year for this month
                  monthData: the class to copy from
  Return Value:	  None
  */
  public MonthBudget(int month, MonthBudget monthData){
    this.month = month;
    this.income = monthData.getIncome();
    this.percentForSavings = monthData.getPercentForSavings();
    this.expenses = monthData.getExpenses();
    this.savings = monthData.getSavings();
    this.fun = monthData.getFun();
    this.total = monthData.getTotal();
  }
  
  /*
  Function Name:	calculateDisposables
  Description:	  Sets the values of savings, fun, and total using percentForSavings
  Parameters:     None
  Return Value:	  None
  */
  public void calculateDisposables(){
    double cost = 0;
    for(int i = 0; i < expenses.length; i++){
      cost += expenses[i].getCost();
    }
    double disposableIncome = income - cost;
    
    // Set savings and fun to 0 if they are broke 
    if (disposableIncome < 0){
      savings = 0;
      fun = 0;
    }
    else {
      this.savings = disposableIncome * (this.percentForSavings/100);
      this.fun = disposableIncome - this.savings;
    }
    total = cost + savings + fun;
  }
  
  // Getters
  public int getMonth(){
    return this.month;
  }
  
  public double getPercentForSavings(){
    return this.percentForSavings;
  }
  
  public double getIncome(){
    return this.income;
  }
  
  public Expense[] getExpenses(){
    return this.expenses;
  }
  
  public double getSavings(){
    return this.savings;
  }
  
  public double getFun(){
    return this.fun;
  }
  
  public double getTotal(){
    return this.total;
  }
  
  public String getMonthName(){
    return monthNames[month];
  }
  
  /*
  Function Name:	getAllValues
  Description:	  returns salary, expenses, and all other information as an array
  Parameters:     None
  Return Value:	  double[] - array with all printable information
  */
  public double[] getAllValues(){
    double[] arr = new double[expenses.length+4];
    int i = 0;
    arr[i++] = income;
    for(; i < expenses.length+1; i++){
      arr[i] = expenses[i-1].getCost();
    }
    arr[i++] = savings;
    arr[i++] = fun;
    arr[i++] = total;
    return arr;
  }
  
  // Setters
  public void setMonth(int month){
    this.month = month;
    calculateDisposables();
  }
  
  public void setPercentageForSavings(double percentForSavings){
    this.percentForSavings = percentForSavings;
    calculateDisposables();
  }
  
  public void setIncome(double income){
    this.income = income;
    calculateDisposables();
  }
  
  public void setExpense(int expenseIdx, Expense expense){
    expenses[expenseIdx] = expense;
    calculateDisposables();
  }
  
  public void setExpenses(Expense[] expense){
    this.expenses = expense;
    calculateDisposables();
  }
  
  public void setSavings(double savings){
    this.savings = savings;
  }
  
  public void setFun(double fun){
    this.fun = fun;
  }
  
  public void setTotal(double total){
    this.total = total;
  }
  
  /*
  Function Name:	setByCategory
  Description:	  Sets value based on category index
  Parameters:     category: category index 
                  value: value to change that variable to
  Return Value:	  None
  */
  public void setByCategory(int category, double value){
    switch(category){
      case 0:
        setIncome(value);
        break;
      case 1:
        expenses[0].setCost(value);
        break;
      case 2:
        expenses[1].setCost(value);
        break;
      case 3:
        expenses[2].setCost(value);
        break;
      case 4:
        expenses[3].setCost(value);
        break;
      case 5:
        setPercentageForSavings(value);
        break;
      default:
        System.out.println("Invalid category");
    }
    calculateDisposables();
  }
}