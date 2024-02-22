import java.util.Scanner;

public class program2dnr {
  // numbers can be >= 0, like paying off your car payment
  // they can change the savings percentage
  // make functions public
  // that's a bold statement, why do you think that
  // split checking double value into two functions, and check both in while loop
  //   - validateDouble, validateDoubleValue. Check for oduble input first, then number
  
  public static void main(String[] args) { 
    // Create a Scanner
    Scanner input = new Scanner(System.in);
    
    // Tell user the usage of program and that they are entering the first month
    double[][] yearData = new double[8][12];
    
    // Get initial data
    // {salary, rent, car, gas, food, savings, fun, total}
    double[] monthData = new double[8];
    monthData = getFirstMonth(input);
    
    // Get savings percent
    double savingsPercent = getInputDouble(input, "Enter savings percentage (ex: 20.5): ");
    
    // Get savings and fun based off salary and savingsPercent
    // Restructure to update the monthData directly? monthData = updateForExtraMoney
    double[] extraMoney = getDisposableIncome(monthData, savingsPercent);
    System.out.println("savings: " + extraMoney[0] + "\nFun: " + extraMoney[1] + "\nTotal: " + extraMoney[2]);
    monthData[5] = extraMoney[0];
    monthData[6] = extraMoney[1];
    monthData[7] = extraMoney[2];
    yearData = updateMonth(yearData, monthData, 0);
    
    // Loop through the rest of the months
    for(int month = 1; month < 12; month++){
      // display data so far
      printYear(yearData, month);
      
      // Let user update values
      
      // Add new values to this thing
      yearData = updateMonth(yearData, monthData, month);
      continue;
    }
    printYear(yearData, 12);
  }
  
  private static double[][] updateMonth(double[][] yearData, double[] monthData, int month){
    for(int category = 0; category < 8; category++){
      yearData[category][month] = monthData[category];
    }
    return yearData;
  }
  
  private static void printYear(double[][] yearData, int month){
    String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", 
                          "Aug", "Sep", "Oct", "Nov", "Dec"};
    String[] categoryNames = {"Salary", "Rent", "Car", "Gas", "Food",
                              "Savings", "Fun", "Total"};
    System.out.print("Cat.\t");
    // Print month names
    for(int i = 0; i < month; i++){
      System.out.print(monthNames[i] + "\t");
    }
    System.out.println("");
    
    // Print data
    for(int category = 0; category < 8; category++){
      System.out.print(categoryNames[category] + "\t");
      for(int i = 0; i < month; i++){
        System.out.print(yearData[category][i] + "\t");
      }
      System.out.println("");
    }
    System.out.println("");
  }
  
  private static double[] getDisposableIncome(double[] monthData, double savingsPercent){
    double expenses = 0.0;
    for(int category = 1; category < 5; category++){
      expenses += monthData[category];
    }
    double disposableIncome = monthData[0] - expenses;
    double savings = disposableIncome * (savingsPercent/100);
    double fun = disposableIncome - savings;
    double total = expenses + savings + fun;
    return new double[] {savings, fun, total};
  }
  
  private static double[] getFirstMonth(Scanner input){
    double salary = getInputDouble(input, "Enter monthly salary: ");
    double rent = getInputDouble(input, "Enter monthly rent: ");
    double car = getInputDouble(input, "Enter monthly car: ");
    double gas = getInputDouble(input, "Enter monthly gas: ");
    double food = getInputDouble(input, "Enter monthly food: ");
    return new double[] {salary, rent, car, gas, food, 0, 0, 0};
  }
  
  private static double getInputDouble(Scanner input, String prompt){
    double num = -1.0;
    // Loop until valid input
    do {
      System.out.print(prompt);
      num = getNextInputDouble(input);
    }
    while (!validateInputDouble(num, 0.0));
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

  private static boolean validateInputDouble(double inputNum1, double constraint){
    if (inputNum1 > constraint){
      return true;
    }
    System.out.println(" > ERROR: Please enter a number greater than " + constraint);
    return false;
  }
}