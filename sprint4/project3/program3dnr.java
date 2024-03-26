/*
Author:        Dominic Rando
Major:         Computer Science
Creation Date: February 20, 2024
Due Date:      February 28, 2024
Course:        CSC 243
Professor:     Dr. DeMarco
Assignment:    Program 2
Filename:      program1dnr.java
Purpose:       Takes in the users monthly income and costs, then goes through every month
               letting them make changes as needed. Then, the program will
               display the values for the entire year
*/

/*
Estimate time: 300 minutes (5 hours)
Actual time: 
*/

/* TO-DO: 
 - change salary to income
 - show the current month's values
 - add headers to all class files
 - look into expense class?
*/

import java.util.Scanner;

public class program3dnr {
  public static void main(String[] args) { 
    // Welcome Message
    printWelcomeMessage();
    
    // Create a Scanner
    Scanner input = new Scanner(System.in);
    
    // Get initial data
    MonthBudget january = getFirstMonth(input);
    
    // Load intial data into the year
    YearBudget yearData = new YearBudget(january);
    
    // Get rest of year
    yearData = getYear(input, yearData);
    
    //print year
    printYear(12, yearData);
  }
  
  public static YearBudget getYear(Scanner input, YearBudget yearData){
    // {salary, rent, car, gas, food, percentage} 
    boolean[] categoryChanged = new boolean[6];
    boolean exitFlag = false;
    
    for(int month = 1; month < 12; month++){      
      // Create new month from last month 
      MonthBudget monthData = new MonthBudget(month, yearData.getMonth(month-1));
      
      // check if user wants to exit
      if (exitFlag){
        yearData.setMonth(month, monthData);
        continue;
      }
      
      // Display table for the year
      printYear(month, yearData);
      
      // Let user update categories
      String choice = "";
      do{
        // Throw warning that this month's costs exceed salary
        //checkTotal(monthData);        
        
        // display menu
        System.out.println("\nChoose a category to change:");
        System.out.println("S)alary");
        System.out.println("R)ent");
        System.out.println("C)ar");
        System.out.println("G)as");
        System.out.println("F)ood");
        System.out.println("P)ercentage for savings");
        System.out.println("V)alues for next month");
        System.out.println("Y)ear table");
        System.out.println("N)ext month");
        System.out.println("E)xit the year");
        
        choice = input.nextLine();
        switch(choice.toLowerCase()) {
          case "s":
            monthData = updateCategory(input, monthData, categoryChanged, 0);
            categoryChanged[0] = true;
            break;
          case "r":
            monthData = updateCategory(input, monthData, categoryChanged, 1);
            categoryChanged[1] = true;
            break;
          case "c":
            monthData = updateCategory(input, monthData, categoryChanged, 2);
            categoryChanged[2] = true;
            break;
          case "g":
            monthData = updateCategory(input, monthData, categoryChanged, 3);
            categoryChanged[3] = true;
            break;
          case "f":
            monthData = updateCategory(input, monthData, categoryChanged, 4);
            categoryChanged[4] = true;
            break;
          case "p":
            monthData = updateCategory(input, monthData, categoryChanged, 5);
            categoryChanged[5] = true;
            break;
          case "v":
            //printMonth(monthData, month);
            break;
          case "y":
            printYear(month, yearData);
            break;
          case "e":
            exitFlag = true;
            break;
          default:
            System.out.println("Please enter a valid value, got " + choice);
        }
      } while (!choice.equals("n") && !choice.equals("e"));
      
      // Set month in the year
      yearData.setMonth(month, monthData);
    }
    return yearData;
  }
  public 
  
  
  public static MonthBudget updateCategory(Scanner input, MonthBudget monthData, boolean[] categoryChanged, int category){
    // Check if category has already been changed
    if (categoryChanged[category]){
      System.out.println("You have already changed that category this year");
      return monthData;
    }
    
    // Get name for category
    String[] categoryNames = {"salary", "rent", "car", "gas", "food",
                              "savings", "fun", "total", "savings percentage"};
    String categoryName = categoryNames[category];
    
    // Get new value for the category
    double num = getInputDouble(input, "Enter a value for " + categoryName + ": ", 0.0);
    monthData.setByCategory(category, num);
    return monthData;
  }
  
  public static void printYear(int currMonth, YearBudget yearData){
    String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", 
                          "Aug", "Sep", "Oct", "Nov", "Dec"};
    String[] categoryNames = {"Salary", "Rent", "Car", "Gas", "Food",
                              "Savings", "Fun", "Total"};
    System.out.println("\nTable for the Year");
    System.out.println("------------------");
    System.out.print("Cat.\t");
    
    // Print month names
    for(int month = 0; month < currMonth; month++){
      System.out.printf("%-11s", monthNames[month]);
    }
    System.out.println("");
    
    // Print data
    for(int category = 0; category < 8; category++){
      System.out.print(categoryNames[category] + "\t");
      for(int month = 0; month < currMonth; month++){
        double value = yearData.getMonth(month).getExpenses()[category];
        String value2 = String.format("%s%-10.2f", "$", value); // limit to 9 chars
        System.out.print(value2);
      }
      System.out.println("");
    }
  }
  
  public static MonthBudget getFirstMonth(Scanner input){
    double salary = getInputDouble(input, "Enter monthly salary: ", 0.0);
    double rent = getInputDouble(input, "Enter monthly rent: ", 0.0);
    double car = getInputDouble(input, "Enter monthly car: ", 0.0);
    double gas = getInputDouble(input, "Enter monthly gas: ", 0.0);
    double food = getInputDouble(input, "Enter monthly food: ", 0.0);
    double savingsPercent = getInputDouble(input, "Enter savings percentage (ex: 20.5): ", 0.0);
    MonthBudget january = new MonthBudget(0, salary, rent, car, gas, food, savingsPercent);
    
    return january;
  }
  
  /*
  Function Name:	getInputDouble
  Description:	  Prompts user for a double until it is supplied
  Parameters:     input: allows program to grab values from cmd line - input/export
                  prompt: message displayed to user - input
                  minValue: value the double must greater than or equal to - input
  Return Value:	  double - number user inputted
  */
  public static double getInputDouble(Scanner input, String prompt, double minValue){
    double num = 0.0;
    // Loop until valid input
    while (true){
      try {
        System.out.print(prompt);
        num = input.nextDouble();
        
        // validate input
        if (num >= minValue){
          break;
        } 
        else{
          System.out.println("> ERROR: Please enter a number greater than " + minValue);
        }
      } 
      catch (Exception e){
        System.out.println("> ERROR: Please enter a double");
        input.nextLine(); // Removes input from the line, assume one-line input
      }
    }
    input.nextLine(); // Consume new-line character from input
    return num;
  }
  
  public static void printWelcomeMessage(){
    System.out.println("------------------------");
    System.out.println("Yearly Budget Calculator");
    System.out.println("------------------------");
  }
}