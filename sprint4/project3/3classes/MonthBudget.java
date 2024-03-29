/*
HEADER
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
  private Expense[] = new Expenses[4];
  
  static final String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", 
                          "Aug", "Sep", "Oct", "Nov", "Dec"};
  
  public MonthBudget(){
    
  }
  
  // income, rent, car, gas, food
  public MonthBudget(int month, double income, double rent, double car, 
                      double gas, double food, double percentForSavings){
    this.month = month;
    this.percentForSavings = percentForSavings;
    this.income = income;
    expenses[0] = new Expense("Rent", rent);
    expenses[0] = new Expense("Car", car);
    expenses[0] = new Expense("Gas", gas);
    expenses[0] = new Expense("Food", food);
    calculateDisposables();
  }
  
  public MonthBudget(int month, MonthBudget monthData){
    /*
    this.month = month;
    this.income = monthData.getIncome();
    this.percentForSavings = monthData.getPercentForSavings();
    this.rent = monthData.getRent();
    this.car = monthData.getCar();
    this.gas = monthData.getGas();
    this.food = monthData.getFood();
    this.savings = monthData.getSavings();
    this.fun = monthData.getFun();
    this.total = monthData.getTotal();
    */
  }
  
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
  
  public Expenses getExpenses(){
    return this.Expenses;
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
  }
  
  public void setExpenses(int expenseIdx, Expenses[] expense){
    this.expenses = expenses;
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
  // MODIFY THIS
  public void setByCategory(int category, double value){
    switch(category){
      case 0:
        setIncome(value);
        break;
      case 1:
        setRent(value);
        break;
      case 2:
        setCar(value);
        break;
      case 3:
        setGas(value);
        break;
      case 4:
        setFood(value);
        break;
      case 5:
        setPercentageForSavings(value);
        break;
      default:
        System.out.println("Invalid category");
    }
  }
}