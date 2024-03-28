/*
HEADER
*/

public class MonthBudget {
  
  // Initial variables
  private int month = 0;
  private double percentForSavings = 0;
  private double income = 0;  
  private double rent = 0;
  private double car = 0;
  private double gas = 0;
  private double food = 0;
  private double savings = 0;
  private double fun = 0;
  private double total;
  
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
    this.rent = rent;
    this.car = car;
    this.gas = gas;
    this.food = food;
    calculateDisposables();
  }
  
  public MonthBudget(int month, MonthBudget monthData){
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
  }
  
  public void calculateDisposables(){
    double cost = rent + car + gas + food;
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
  
  public double getRent(){
    return this.rent;
  }
  
  public double getCar(){
    return this.car;
  }
  
  public double getGas(){
    return this.gas;
  }
  
  public double getFood(){
    return this.food;
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
  
  public double[] getExpenses(){
    double[] expenses = {this.income, this.rent, this.car, this.gas, this.food, this.savings, this.fun, this.total};
    return expenses;
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
  
  public void setRent(double rent){
    this.rent = rent;
    calculateDisposables();
  }
  
  public void setCar(double car){
    this.car = car;
    calculateDisposables();
  }
  
  public void setGas(double gas){
    this.gas = gas;
    calculateDisposables();
  }
  
  public void setFood(double food){
    this.food = food;
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