// Exercise from Chapter 3 page 34

import java.util.Scanner;

public class shape {
  public static void main(String[] args) { 
  
    // Create a Scanner
    Scanner input = new Scanner(System.in);

    // Get input numbers
    double[] inputs = getInputDouble2(input, 
      "Enter the width and height of a rectangle: ");
      
    // Assign input numbers
    double width = inputs[0];
    double height = inputs[1];
    
    // Calculate results
    double perimeter = (2*width) + (2*height);
    double area = width*height;
    double lengthOfDiagonal = Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
    
    // Display results
    printResults(perimeter, area, lengthOfDiagonal);
  }
  
  private static void printResults(double perimeter, double area, double lengthOfDiagonal){
    // Display results
    System.out.println("Perimeter: \t" + perimeter);
    System.out.println("Area: \t\t" + area);
    System.out.println("Diagonal: \t" + lengthOfDiagonal);
    printSize(area);
  }
  
  private static void printSize(double area){
    int areaModified = (int)(area/100);
    switch(areaModified) {
      case 1: 
        System.out.println("The size is small");
        break;
      case 2:
        System.out.println("The size is medium");
        break;
      case 3:
        System.out.println("The size is large");
        break;
      case 4:
        System.out.println("The size is XLarge");
        break;
      default:
        if (areaModified>5) {
          System.out.println("The size is Huge");
        } else {
          System.out.println("Rectangle is too small!");
        }
    }
  }
  
  private static double[] getInputDouble2(Scanner input, String prompt){
    double width = -1;
    double height = -1;
    // Loop until valid input
    do {
      System.out.print(prompt);
      width = getNextInputDouble(input);
      height = getNextInputDouble(input);
    }
    while (!validateInputDouble(width, height, 0));
    return new double[]{width, height};
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