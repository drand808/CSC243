import java.util.Scanner;

public class program2dnr {
  // make functions public
  // update salary and fun to equal 0 when when they are negative
  // This should change it so that the total represents a negative value
  // move functions to a nice order
  // put updating the yearly table into a function to lower clutter in main
  
  public static void main(String[] args) { 
    // Create a Scanner
    Scanner input = new Scanner(System.in);
    
    // Tell user the usage of program and that they are entering the first month
    double[][] yearData = new double[8][12];
    
    // Get initial data
    // {salary, rent, car, gas, food, savings, fun, total, percentage}
    double[] monthData = new double[9];
    monthData = getFirstMonth(input);

    // Update year for first month
    yearData = updateMonth(yearData, monthData, 0);
    
    boolean exitFlag = false;
    input.nextLine(); // clear random input
    
    // {salary, rent, car, gas, food, percentage}
    boolean[] categoryChanged = new boolean[9];
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
        
        // Throw warning that 
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
    System.out.println("\nTable for Next Month");
    System.out.println("----------------------");
    System.out.println("Cat.\t" + monthNames[month]);
    
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
  
  public static double[] updateCategory(Scanner input, double[] monthData, boolean[] categoryChanged, int category){
    if (categoryChanged[category]){
      System.out.println("You have already changed that category this year");
      return monthData;
    }
    double num = getInputDouble(input, "- New value: ", 0.0);
    if (num != -1){
      monthData[category] = num;
    }
    monthData = updateDisposableIncome(monthData, monthData[8]);
    input.nextLine();
    return monthData;
  }
  
  public static double[][] updateMonth(double[][] yearData, double[] monthData, int month){
    monthData = updateDisposableIncome(monthData, monthData[8]);
    for(int category = 0; category < 8; category++){
      yearData[category][month] = monthData[category];
    }
    return yearData;
  }
  
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
  
  public static double[] updateDisposableIncome(double[] monthData, double savingsPercent){
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
  
  public static double[] getFirstMonth(Scanner input){
    double salary = getInputDouble(input, "Enter monthly salary: ", 0.0);
    double rent = getInputDouble(input, "Enter monthly rent: ", 0.0);
    double car = getInputDouble(input, "Enter monthly car: ", 0.0);
    double gas = getInputDouble(input, "Enter monthly gas: ", 0.0);
    double food = getInputDouble(input, "Enter monthly food: ", 0.0);
    double[] monthData = {salary, rent, car, gas, food, 0, 0, 0, 0};
    
    // Get savings percent
    double savingsPercent = getInputDouble(input, "Enter savings percentage (ex: 20.5): ", 0.0);
    
    // Get savings and fun based off salary and savingsPercent
    monthData = updateDisposableIncome(monthData, savingsPercent);
    
    return monthData;
  }
  
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
    return num;
  }
}