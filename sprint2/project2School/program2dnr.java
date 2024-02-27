import java.util.Scanner;

public class program2dnr {
  // numbers can be >= 0, like paying off your car payment
  // they can change the savings percentage
  // make functions public
  // split checking double value into two functions, and check both in while loop
  //   - validateDouble, validateDoubleValue. Check for oduble input first, then number
  // throw error when values exceed income. don't stop them, just tell them they're losing money
  /*
  format yearly table like: 
  --------------
  |Yearly table|
  |cat. months |
  --------------
  maybe without 
  */
  
  public static void main(String[] args) { 
    // Create a Scanner
    Scanner input = new Scanner(System.in);
    
    // Tell user the usage of program and that they are entering the first month
    double[][] yearData = new double[8][12];
    
    // Get initial data
    // {salary, rent, car, gas, food, savings, fun, total, percentage}
    double[] monthData = new double[9];
    monthData = getFirstMonth(input);
    // put savings percent and extramoney expressions into get firt month so it reads:
    // monthData = getFirstMonth(input);
    // yearData = updateMonth(yearData, monthData, 0);
    yearData = updateMonth(yearData, monthData, 0);
    
    boolean exitFlag = false;
    input.nextLine();
    
    // Loop through the rest of the months
    for(int month = 1; month < 12; month++){
      // check if user wants to exit
      if (exitFlag){
        yearData = updateMonth(yearData, monthData, month);
        continue;
      }
      
      // display data so far
      printYear(yearData, month);
      
      // Let user update values. return yearValue? but I have to handle exitFlag
      boolean continueFlag = false;
      String choice = "";
      do{  
        
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
        System.out.println("N)ext month");
        System.out.println("E)xit the year");
        
        choice = input.nextLine();
        switch(choice.toLowerCase()) {
          case "s":
            monthData = updateCategory(input, monthData, 0);
            break;
          case "r":
            monthData = updateCategory(input, monthData, 1);
            break;
          case "c":
            monthData = updateCategory(input, monthData, 2);
            break;
          case "g":
            monthData = updateCategory(input, monthData, 3);
            break;
          case "f":
            monthData = updateCategory(input, monthData, 4);
            break;
          case "p":
            monthData = updateCategory(input, monthData, 8);
            break;
          case "v":
            printCurrentMonth(monthData, month);
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
      
      // Add new values to this thing
      yearData = updateMonth(yearData, monthData, month);
      continue;
    }
    printYear(yearData, 12);
  }
  
  public static void printCurrentMonth(double[] monthData, int month){
    String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", 
                          "Aug", "Sep", "Oct", "Nov", "Dec"};
    String[] categoryNames = {"Salary", "Rent", "Car", "Gas", "Food",
                              "Savings", "Fun", "Total"};
    
    System.out.println("\nCat.\t" + monthNames[month]);
    
    // Print data
    for(int category = 0; category < 8; category++){
      System.out.print(categoryNames[category] + "\t");
      System.out.print(monthData[category] + "\t");
      System.out.println("");
    }
  }
  
  public static void checkTotal(double[] monthData){
    double savings = monthData[5];
    double fun = monthData[6];
    if (savings < 0 || fun < 0){
      System.out.println("WARNING: current costs exceed salary");
    }
  }
  
  public static double[] updateCategory(Scanner input, double[] monthData, int category){
    double num = getInputDouble(input, "- New value: ");
    if (num != -1){
      monthData[category] = num;
    }
    monthData = updateDisposableIncome(monthData, monthData[8]);
    input.nextLine();
    return monthData;
  }
  
  private static double[][] updateMonth(double[][] yearData, double[] monthData, int month){
    monthData = updateDisposableIncome(monthData, monthData[8]);
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
    System.out.print("\nCat.\t");
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
  }
  
  private static double[] updateDisposableIncome(double[] monthData, double savingsPercent){
    double expenses = 0.0;
    for(int category = 1; category < 5; category++){
      expenses += monthData[category];
    }
    double disposableIncome = monthData[0] - expenses;
    double savings = disposableIncome * (savingsPercent/100);
    double fun = disposableIncome - savings;
    double total = expenses + savings + fun;
    monthData[5] = savings;
    monthData[6] = fun;
    monthData[7] = total;
    monthData[8] = savingsPercent;
    
    return monthData;
  }
  
  private static double[] getFirstMonth(Scanner input){
    double salary = getInputDouble(input, "Enter monthly salary: ");
    double rent = getInputDouble(input, "Enter monthly rent: ");
    double car = getInputDouble(input, "Enter monthly car: ");
    double gas = getInputDouble(input, "Enter monthly gas: ");
    double food = getInputDouble(input, "Enter monthly food: ");
    double[] monthData = {salary, rent, car, gas, food, 0, 0, 0, 0};
    
    // Get savings percent
    double savingsPercent = getInputDouble(input, "Enter savings percentage (ex: 20.5): ");
    
    // Get savings and fun based off salary and savingsPercent
    // Restructure to update the monthData directly? monthData = updateForExtraMoney
    monthData = updateDisposableIncome(monthData, savingsPercent);
    
    return monthData;
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