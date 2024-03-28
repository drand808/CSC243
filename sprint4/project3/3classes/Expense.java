/*
HEADER
*/

public class Expense {
  
  // Initial variables
  private String name = "UNASSIGNED";
  private double cost = 0;
  
  public Expense(){
  }
  
  // income, rent, car, gas, food
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