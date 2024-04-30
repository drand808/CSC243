/*
Author:        Dominic Rando
Major:         Computer Science
Creation Date: Arpil 11, 2024
Due Date:      April 10, 2024
Course:        CSC 243
Professor:     Dr. DeMarco
Assignment:    Program 4
Filename:      program4dnr.java
Purpose:       Takes in the users monthly income and costs, then goes through every month
               letting them make changes as needed. Then, the program will
               display the values for the entire year. Implemented with classes
*/

import java.util.Scanner;

public class program4dnr {
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
  
  /*
  Function Name:	getYear
  Description:	  Get values for an entire year
  Parameters:     input: allows program to grab values from cmd line - input/export
                  yearData: the data for the year - export
  Return Value:	  YearBudget - the data for the year
  */
  public static YearBudget getYear(Scanner input, YearBudget yearData){
    String[] categoryNames = {"Salary", "Rent", "Car", "Gas", "Food",
                              "Savings", "Fun", "Total"};
    // subtract for savings, fun, total. add for percentage
    boolean[] categoryChanged = new boolean[categoryNames.length-2];
    boolean exitFlag = false;
    
    for(int month = 1; month < 12; month++){      
      // Create new month from last month 
      MonthBudget monthData = new MonthBudget(month, yearData.getMonth(month-1));
      
      // check if user wants to exit
      if (exitFlag){
        yearData.setMonth(month, monthData);
        continue;
      }
      
      // Show data so far
      printYear(month, yearData);
      
      // Let user update categories
      String choice = "";
      do{
        // Throw warning that this month's costs exceed salary
        checkTotal(monthData);        
        
        // Control which category to changed
        int categoryIdx = -1;
        
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
            categoryIdx = 0;
            break;
          case "r":
            categoryIdx = 1;
            break;
          case "c":
            categoryIdx = 2;
            break;
          case "g":
            categoryIdx = 3;
            break;
          case "f":
            categoryIdx = 4;
            break;
          case "p":
            categoryIdx = 5;
            break;
          case "v":
            printMonth(month, monthData, categoryNames);
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
        // Change category value if selected
        if(categoryIdx != -1){
          monthData = updateCategory(input, categoryIdx, monthData, categoryChanged, categoryNames);
          categoryChanged[categoryIdx] = true;
        }
      } while (!choice.equals("n") && !choice.equals("e"));
      
      // Set month in the year
      yearData.setMonth(month, monthData);
    }
    return yearData;
  }
  
  /*
  Function Name:	checkTotal
  Description:	  outputs a warning message if the costs exceed the salary
  Parameters:     monthData: array representing the data for this month - input
  Return Value:	  void
  */  
  public static void checkTotal(MonthBudget monthData){
    double income = monthData.getIncome();
    double total = monthData.getTotal();
    if (income < total){
      System.out.println("> WARNING: current costs exceed salary");
    }
  }
  
  /*
  Function Name:	printMonth
  Description:	  outputs the values for the current month the user is working on
  Parameters:     month: index of this month - input
                  monthData: the data for this month - input
                  categoryNames: array of the names for every category
  Return Value:	  void
  */ 
  public static void printMonth(int month, MonthBudget monthData, String[] categoryNames){
    System.out.println("\nTable for Next Month");
    System.out.println("----------------------");
    System.out.println("Cat.\t" + monthData.getMonthName());
    
    // Print data
    double[] tableData = monthData.getAllValues();
    for(int category = 0; category < categoryNames.length; category++){
      System.out.print(categoryNames[category] + "\t");
      System.out.printf("$%.2f", tableData[category]);
      System.out.println("");
    }
  }
  
  /*
  Function Name:	updateCategory
  Description:	  Updates the value for a category for a given month
  Parameters:     input: allows program to grab values from cmd line - input/export
                  category: index for the chosen category
                  monthData: The data for this month - export
                  categoryChanged: array of categories that have been changed - input
                  categoryNames: array of the names for every category
  Return Value:	  MonthBudget - the data for this month
  */
  public static MonthBudget updateCategory(Scanner input, int category, MonthBudget monthData, boolean[] categoryChanged, String[] categoryNames){
    // Check if category has already been changed
    if (categoryChanged[category]){
      System.out.println("You have already changed that category this year");
      return monthData;
    }
    
    // Put percentage after expenses, add 1 for salary
    String categoryName = "";
    if(category == monthData.getExpenses().length+1){
      categoryName = "Percentage for Savings";
    } else {
      categoryName = categoryNames[category];
    }

    // Get new value for the category
    double num = getInputDouble(input, "Enter a value for " + categoryName.toLowerCase() + ": ", 0.0);
    monthData.setByCategory(category, num);
    return monthData;
  }
  
  /*
  Function Name:	printYear
  Description:	  outputs the data for the whole year
  Parameters:     currMonth: index of the month to print up to - input
                  yearData: The data for this year - input    
  Return Value:	  void
  */ 
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
    for(int category = 0; category < categoryNames.length; category++){
      System.out.print(categoryNames[category] + "\t");
      for(int month = 0; month < currMonth; month++){
        double data = yearData.getMonth(month).getAllValues()[category];
        double value = data;
        printCurrency(value);
      }
      System.out.println("");
    }
  }
  
  /*
  Function Name:	printCurrency
  Description:	  Helper method for printing a value as currency
  Parameters:     value - number to print as USD
  Return Value:	  void - none
  */
  public static void printCurrency(double value){
    String message = String.format("%s%-10.2f", "$", value); // limit to 9 chars
    System.out.print(message);
  }
  
  /*
  Function Name:	getFirstMonth
  Description:	  Gets value for each category for the first month
  Parameters:     input: allows program to grab values from cmd line - input/export
  Return Value:	  MonthBudget - the data for this month
  */
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
  
  /*
  Function Name:	printWelcomeMessage
  Description:	  Tells user program is running
  Parameters:     None
  Return Value:	  void - none
  */
  public static void printWelcomeMessage(){
    System.out.println("------------------------");
    System.out.println("Yearly Budget Calculator");
    System.out.println("------------------------");
  }
}