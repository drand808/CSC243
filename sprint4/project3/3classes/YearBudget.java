/*
HEADER
*/

public class YearBudget {
  
  // Initial variables
  private MonthBudget[] months = new MonthBudget[12];
  
  public YearBudget(){
  }
  
  // income, rent, car, gas, food
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