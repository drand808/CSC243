// Name: Donna DeMarco
// Major: CompSci
// Creation Date: Feb 20, 2024
// Due Date: Feb 28, 2024
// Course: CSC243-20
// Professor: DeMarco
// Assignment: Program 2
// Filename: project2dnr.java
// Purpose: this is a template file to use as a starting point for java programs

import java.util.Scanner;

public class program1dnr {
  public static void main(String[] args) { 
    // Mean yearly personal income in 2020 is around $42,000
    // Create a Scanner for input
    Scanner input = new Scanner(System.in);
    double[8][12] yearTable;
    
    // Initial values
    double[] initialValues = getInitialValues(input);
    monthValues = initialValues;
    
    double savingsPercentage = getInputDouble(input, 
      "Enter monthly savings as a percent. Example, '45.5': ");
    
    // Enter Initial Values
    yearTable = setYearTable(yearTable, monthValues, savingsPercentage, 0);
    
    // Loop for rest of year, chance to update values
    for(int month = 1; i < 12; i++){
      displayYearlyTable(yearTable);
      yearValues = getChanges();
      yearTable = setYearTable(yearTable, yearValues, savingsPercentage, month);
    }
    
    // Output final yearly table
    displayYearlyTable(yearTable);
  }
  
  private static double[][] setYearTable
        (double[][] yearTable, double[] monthData, double savingsPercentage, int month){
    double savings = getSavingsMoney(monthData, savingsPercentage);
    double fun = getFunMoney(monthData, savingsPercentage);
    double total;
    
    monthData[5] = savings;
    monthData[6] = fun;
    for(int category = 0; category < monthData.length; category++){
      // income, rent, car, gas, food, savings, fun
      yearTable[category][month] = monthData[category];
      total += monthData[category];
    }
    yearTable[8][month] = total;
    return yearTable;
  }
  
  private static double getSavingsMoney(double[] monthData, double savingsPercentage){
    
  }
  
  private static double[] getInitialValues(Scanner input){
    double income = getInputDouble(input, "Enter monthly income: ");
    double rent = getInputDouble(input, "Enter monthly cost of rent: ");
    double car = getInputDouble(input, "Enter monthly cost of car payment: ");
    double gas = getInputDouble(input, "Enter monthly cost of gas: ");
    double food = getInputDouble(input, "Enter monthly cost of food: ");
    return [income, rent, car, gas, food, 0, 0];  // Extra 0 for savings and fun
  }
  
  private static double[] getInputDouble(Scanner input, String prompt){
    double num = -1
    // Loop until valid input
    do {
      System.out.print(prompt);
      width = getNextInputDouble(input);
    }
    while (!validateInputDouble(width, height, 0));
    return num;
  }

  private static double getNextInputDouble(Scanner input){
    // Test if input is an integer
    try {
      return input.nextDouble();
    }
    catch (Exception e){
      System.out.println(" > ERROR: Please make sure you are entering a number");
      input.nextLine(); // Removes input from the line, assume one-line input
      return -1;
    }
  }
  
   private static boolean validateInputDouble(double inputNum1, double inputNum2, double constraint){
    if (inputNum1 > constraint && inputNum2 > constraint){
      return true;
    }
    System.out.println(" > ERROR: Please enter a number greater than " + constraint);
    return false;
  }
}