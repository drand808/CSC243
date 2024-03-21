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

import java.util.Scanner;

public class program2dnr {
  public static void main(String[] args) { 
    // Create a Scanner
    Scanner input = new Scanner(System.in);
    
    // Table for the entire year
    double[][] yearData = new double[8][12];
    
    // Get initial data
    // {salary, rent, car, gas, food, savings, fun, total, percentage}
    double[] monthData = new double[9];
    monthData = getFirstMonth(input);

    // Update year for first month
    yearData = updateMonth(yearData, monthData, 0);
    
    boolean exitFlag = false;
    
    // {salary, rent, car, gas, food, percentage}
    boolean[] categoryChanged = new boolean[9];
    
    // Get the rest of the months
    yearData = getYear(input, yearData, monthData);
    printYear(yearData, 12);
  }
  
  /*
  Function Name:	getInputdouble
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
  Function Name:	getFirstMonth
  Description:	  Gets value for each category for the first month
  Parameters:     input: allows program to grab values from cmd line - input/export
  Return Value:	  double[] - the data for this month
  */
  public static double[] getFirstMonth(Scanner input){
    double salary = getInputDouble(input, "Enter monthly salary: ", 0.0);
    double rent = getInputDouble(input, "Enter monthly rent: ", 0.0);
    double car = getInputDouble(input, "Enter monthly car: ", 0.0);
    double gas = getInputDouble(input, "Enter monthly gas: ", 0.0);
    double food = getInputDouble(input, "Enter monthly food: ", 0.0);
    double savingsPercent = getInputDouble(input, "Enter savings percentage (ex: 20.5): ", 0.0);
    double[] monthData = {salary, rent, car, gas, food, 0, 0, 0, savingsPercent};
    
    // Get savings percent
    
    // Get savings and fun based off salary and savingsPercent
    monthData = updateDisposableIncome(monthData);
    
    return monthData;
  }
  
  /*
  Function Name:	getYear
  Description:	  Get values for an entire year
  Parameters:     input: allows program to grab values from cmd line - input/export
                  yearData: 2-d array representing the table for the year - export
                  monthData: array representing the data for this month - input/export
  Return Value:	  double[][] - the data for this year
  */
  public static double[][] getYear(Scanner input, double[][] yearData, double[] monthData){
    // {salary, rent, car, gas, food, percentage}
    boolean[] categoryChanged = new boolean[9];
    
    boolean exitFlag = false;
    for(int month = 1; month < 12; month++){
      // check if user wants to exit
      if (exitFlag){
        yearData = updateMonth(yearData, monthData, month);
        continue;
      }
      
      // display data so far
      printYear(yearData, month);
      
      // Let user update categories
      String choice = "";
      do{
        // Throw warning that this month's costs exceed salary
        checkTotal(monthData);        
        
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
            monthData = updateCategory(input, monthData, categoryChanged, 8);
            categoryChanged[8] = true;
            break;
          case "v":
            printMonth(monthData, month);
            break;
          case "y":
            printYear(yearData, month);
            break;
          case "e":
            exitFlag = true;
            break;
          default:
            System.out.println("Please enter a valid value, got " + choice);
        }
      } while (!choice.equals("n") && !choice.equals("e"));
      
      // Add new values for this month
      yearData = updateMonth(yearData, monthData, month);
      continue;
    }
    return yearData;
  }
  
  /*
  Function Name:	updateCategory
  Description:	  Updates the value for a category for a given month
  Parameters:     input: allows program to grab values from cmd line - input/export
                  monthData: array representing the data for this month - export
                  categoryChanged: array of categories that have been changed - input/export
                  category: index for the chosen category
  Return Value:	  double[] - the data for this month
  */
  public static double[] updateCategory(Scanner input, double[] monthData, boolean[] categoryChanged, int category){
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
    double num = getInputDouble(input, "Enter a value for " + categoryName + " : ", 0.0);
    monthData[category] = num;
    
    // Update fun, savings, total
    monthData = updateDisposableIncome(monthData);
    return monthData;
  }
  
  /*
  Function Name:	updateDisposableIncome
  Description:	  grabs the disposable income and updates values in monthData array
  Parameters:     monthData: array representing the data for this month - input/export
  Return Value:	  double[][] - the data for this year
  */
  public static double[] updateDisposableIncome(double[] monthData){
    // Get total cost of bills
    double expenses = 0.0;
    for(int category = 1; category < 5; category++){
      expenses += monthData[category];
    }
    
    // Get non-input values
    double savingsPercent = monthData[8];
    double disposableIncome = monthData[0] - expenses;
    double savings = disposableIncome * (savingsPercent/100);
    double fun = disposableIncome - savings;
    
    // Set savings and fun to 0 if they are broke 
    if (disposableIncome < 0){
      savings = 0;
      fun = 0;
    }
    
    // Get total and update values
    double total = expenses + savings + fun;
    monthData[5] = savings;
    monthData[6] = fun;
    monthData[7] = total;
    monthData[8] = savingsPercent;
    
    return monthData;
  }  
  
  /*
  Function Name:	updateMonth
  Description:	  updates the year table with this month's data
  Parameters:     yearData: 2-d array representing data for the year - export
                  monthData: array representing the data for this month - input
                  month: index of this month
  Return Value:	  double[][] - the data for this year
  */
  public static double[][] updateMonth(double[][] yearData, double[] monthData, int month){
    monthData = updateDisposableIncome(monthData);
    for(int category = 0; category < 8; category++){
      yearData[category][month] = monthData[category];
    }
    return yearData;
  }
  
  /*
  Function Name:	checkTotal
  Description:	  outputs a warning message if the costs exceed the salary
  Parameters:     monthData: array representing the data for this month - input
  Return Value:	  void
  */  
  public static void checkTotal(double[] monthData){
    double salary = monthData[0];
    double total = monthData[7];
    if (salary < total){
      System.out.println("> WARNING: current costs exceed salary");
    }
  }
  
  /*
  Function Name:	printMonth
  Description:	  outputs the values for the current month the user is working on
  Parameters:     monthData: array representing the data for this month - input
                  month: index of this month - input
  Return Value:	  void
  */ 
  public static void printMonth(double[] monthData, int month){
    String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", 
                          "Aug", "Sep", "Oct", "Nov", "Dec"};
    String[] categoryNames = {"Salary", "Rent", "Car", "Gas", "Food",
                              "Savings", "Fun", "Total"};
    System.out.println("\nTable for Next Month");
    System.out.println("----------------------");
    System.out.println("Cat.\t" + monthNames[month]);
    
    // Print data
    for(int category = 0; category < 8; category++){
      System.out.print(categoryNames[category] + "\t");
      System.out.printf("$%.2f", monthData[category]);
      System.out.println("");
    }
  }
  
  /*
  Function Name:	printYear
  Description:	  outputs the data for the whole year
  Parameters:     yearData: 2-d array representing the data for this year - input    
                  month: index of this month to print up to - input
  Return Value:	  void
  */ 
  public static void printYear(double[][] yearData, int month){
    String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", 
                          "Aug", "Sep", "Oct", "Nov", "Dec"};
    String[] categoryNames = {"Salary", "Rent", "Car", "Gas", "Food",
                              "Savings", "Fun", "Total"};
    System.out.println("\nTable for the Year");
    System.out.println("------------------");
    System.out.print("Cat.\t");
    // Print month names
    for(int i = 0; i < month; i++){
      System.out.printf("%-11s",monthNames[i]);
    }
    System.out.println("");
    
    // Print data
    for(int category = 0; category < 8; category++){
      System.out.print(categoryNames[category] + "\t");
      for(int i = 0; i < month; i++){
        double value = yearData[category][i];
        String value2 = String.format("%s%-10.2f", "$", value); // limit to 9 chars
        System.out.print(value2);
      }
      System.out.println("");
    }
  }
}