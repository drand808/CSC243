/*
HEADER
*/

public class YearBudget {
  
  // Initial variables
  private MonthBudget[] months = new MonthBudget[12];
  
  static final String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", 
                          "Aug", "Sep", "Oct", "Nov", "Dec"};
  
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