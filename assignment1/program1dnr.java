import java.util.Scanner;

public class program1dnr {
  public static void main(String[] args) { 
    // Constant interest rate as a percentage
    final double INTEREST_RATE = 1.05;
  
    // Create a Scanner
    Scanner input = new Scanner(System.in);

    // Enter number of years until retirement
    int years = getIntInput(input, 
      "Enter number of years until retirement as an integer: ");
    
    // Enter amount of money saved annually
    int money = getIntInput(input, 
      "Enter amount of money as an integer: ");
      
    // Calculate retirement money
    double moneyRetirement = money * (Math.pow(INTEREST_RATE, years));
    
    // Display results
    System.out.println("Money at retirement: $" + moneyRetirement);
  }
  
  private static int getIntInput(Scanner input, String prompt){
    int num;
    // Loop until valid input
    do {
      num = promptForInt(input, prompt);
    }
    while (!validateIntegerInput(num, 0));
    return num;
  }
  
  private static int promptForInt(Scanner input, String message){
    System.out.print(message);
    // test input
    try {
      return input.nextInt();
    }
    catch (Exception e){
      System.out.println(" > ERROR: Please make sure you are entering an integer");
      input.nextLine(); // Removes input from the line, assume one-line input
      return -1;
    }
  }
  
  private static boolean validateIntegerInput(int inputNum, int constraint){
    if (inputNum > constraint){
      return true;
    }
    System.out.println(" > ERROR: Please enter an integer greater than " + constraint);
    return false;
  }
}