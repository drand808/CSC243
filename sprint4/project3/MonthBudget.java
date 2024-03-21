public class MonthBudget {
  private int month;
  public MonthBudget(){
    this.month = 0;
  }
  
  public MonthBudget(int month){
    this.month = month;
  }
  
  public int getMonth(){
    return this.month;
  }
  
  public void setMonth(int month){
    this.month = month;
  }
}