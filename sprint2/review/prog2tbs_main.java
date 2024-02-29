/*
Author: Tiberius Shaub
Major: Information Technology
Creation Date: February 27, 2024
Due Date: February 28, 2024 - 10 PM
Course: CSC243 01
Professor Name: Prof. Donna DeMarco
Assignment: 2 - Yearly Budget
Filename: prog2tbs_main.java

Purpose: 

Make a budgeting application for a year. 
Divide the budget into columns for each month. 
First row is for the budget. 

Static and chosen by the user; this will be the same for every month, with an 
option to change once if the user gets a raise/promotion during the year. 
All expenses will be similarly fixed monthly, with an option for a single change 
to occur that will carry over to every additional month. 

User will choose the monthly expenses for "Rent", "Car", "Gas", and "Food". 
Leftover money in the budget will be divided between the user's savings and "Fun" 
money using a fixed percentage chosen by the user at the start and is consistent 
the whole year.

*/

//imports
import java.util.Scanner;

// my month object to be manipulated by the main program
class Month 
{
  private int income;
  private int rent;
  private int car;
  private int gas;
  private int food;

  private int[] setupStatus = new int[6];
  private int toSave;
  private int leftover;

  private int savings;
  private int fun;
  private int errorCode;
  private String[] errorMessages = new String[6];

  // Constructor to initialize variables.
  public Month(int toSave) 
  {
    this.toSave = toSave;
      
    this.income = -1;
    this.rent = -1;
    this.car = -1;
    this.gas = -1;
    this.food = -1;

    this.leftover = -1;
    this.savings = -1;
    this.fun = -1;
      
    this.errorCode = 0;
    this.errorMessages[0] = "No Errors Detected.";
    this.errorMessages[1] = "Monthly budget is not balanced, re-enter values.";
    this.errorMessages[2] = "Income and expenses not fully set, cannot compute savings or fun.";

    for(int incrementor = 0; incrementor<5; incrementor++)
    {
      this.setupStatus[incrementor] = 0;
    }
  }

  // Getters and setters for each variable (optional)
  public void setIncome(int income) 
  {
    if (income >= 0 && income >= (this.rent + this.car + this.gas + this.food))
    {
      this.income = income;
      this.setupStatus[0] = 1;
      this.errorCode = 0;
      return;
    }
    else
    {
      this.errorCode = 1;
      return;
    }
  }

  public void setRent(int rent) 
  {
    if (rent >= 0 && this.income >= (rent + this.car + this.gas + this.food))
    {
      this.rent = rent;
      this.setupStatus[1] = 1;
      this.errorCode = 0;
      return;
    }
    else
    {
      this.errorCode = 1;
      return;
    }
  }

  public void setCar(int car) 
  {
    if (car >= 0 && this.income >= (this.rent + car + this.gas + this.food))
    {
      this.car = car;
      this.setupStatus[2] = 1;
      this.errorCode = 0;
      return;
    }
    else
    {
      this.errorCode = 1;
      return;
    }
  }

  public void setGas(int gas) 
  {
    if (gas > 0 && this.income >= (this.rent + this.car + gas + this.food))
    {
      this.gas = gas;
      this.setupStatus[3] = 1;
      this.errorCode = 0;
      return;
    }
    else
    {
      this.errorCode = 1;
      return;
    }
  }

  public void setFood(int food) 
  {
    if (food > 0 && this.income >= (this.rent + this.car + this.gas + food))
    {
      this.food = food;
      this.setupStatus[4] = 1;
      this.errorCode = 0;
      return;
    }
    else
    {
      this.errorCode = 1;
      return;
    }
  }

  //getters
  public int getIncome() 
  {
    return this.income;
  }

  public int getRent() 
  {
    return this.rent;
  }

  public int getCar() 
  {
    return this.car;
  }

  public int getGas() 
  {
    return this.gas;
  }

  public int getFood() 
  { 
    return this.food;
  }

  public int getSavings() 
  { 
    int dump = setSavingsFun();
    // application must handle -1 returns for fun and savings.
    return this.savings;
  }

  public int getFun() 
  {
    int dump = setSavingsFun();
    // application must handle -1 returns for fun and savings.
    return this.fun;
  }

  public int getErrorCode()
  {
    return this.errorCode;
  }

  public String getErrorMessage(int codeNum)
  {
    return this.errorMessages[codeNum];
  }
    
  //this function is called to generate savings and fun values on demand by the object.
  private int setSavingsFun()
  {
    //this section is for checking that all the values in Month have been properly set by the application
    this.setupStatus[5] = 0;
    
    for(int incrementor = 0; incrementor < 5; incrementor++)
    {
      this.setupStatus[5] += this.setupStatus[incrementor];
    } 
      
    //if so, the savings and fun values are calculated for the Month.
    if (this.setupStatus[5] == 5)
    {
      // the leftover value is used here as an in-between step
      this.leftover = this.income - (this.rent + this.car + this.gas + this.food);

      //if the leftover value is greater than zero, than the budget is balanced and calculations can proceed.
      if (this.leftover >= 0)
      {
        this.savings = (this.leftover * this.toSave) / 100;
        
        this.fun     = (this.leftover * (100 - this.toSave)) / 100;
        while ((this.savings + this.fun) < this.leftover)
        {  
          this.savings++; 
        }
        this.leftover = 0;
        this.errorCode = 0;
        return 1;
      }
      this.errorCode = 1;
      return 0;
    }
    
    //values must be reset due to entering failure mode
    this.savings = -1;      
    this.fun = -1;
    this.leftover = -1;
    this.errorCode = 2;
    return 0;
    
    // application must handle -1 returns for fun and savings.
  }
}

//This program calculates monthly budgets for a year
public class prog2tbs_main 
{
  //start of the main function
  public static void main(String[] args) 
  {
    //display the application welcome text.
    System.out.println( "                                                      ");
    System.out.println( "******************************************************");
    System.out.println( "*              Yearly Budget Application             *");
    System.out.println( "******************************************************");
    System.out.println( "*                 For a stable income                *");
    System.out.println( "******************************************************");

    //declare the program's variables
    char calcStatus = 'Y';

    int errorCode = -1;
    String errorMessage = " ";

    char monthChanges = 'm';
    int toSave = -1;
    int monthCount = 0;
    int monthTotal = 12;
    
    Scanner input = new Scanner(System.in);
    
    //this is where we find how much money the user wants to save as a percentage of total leftover.
    while(toSave < 0 || toSave > 100)
    {
      System.out.print("How much of your leftover money will be saved?: %");
      toSave = input.nextInt();
      if(toSave < 0 || toSave > 100)
      {
        System.out.println("Please enter a value between 0 and 100.");
      }
    }

    //month setup
    Month[] fullYear = new Month[monthTotal];
    for (monthCount = 0; monthCount < monthTotal; monthCount++) {
      fullYear[monthCount] = new Month(toSave);
    }

    String[] monthNames = {
      "January", "February", "March", "April",
      "May", "June", "July", "August",
      "September", "October", "November", "December"
    };
    
    //here the application will create and fill up new Month objects.

    //main loop for the whole year
    for (monthCount = 0; monthCount < monthTotal; monthCount++) 
    { 
      //this option to autofill is only allowed after january
      if (monthCount > 0)
      {
        //user interaction to allow for changes to the months as they go on, but still be optional to fill in from last month
        for(monthChanges = 'm'; monthChanges == 'm'; )
        {
          //this bit of user interaction requires a valid input to continue the program.
          System.out.println("Would you like to make any changes for " + monthNames[monthCount] + "? (Y/n)");
          monthChanges = input.next().charAt(0);
          if(monthChanges != 'y' && monthChanges != 'Y' && monthChanges != 'N' && monthChanges != 'n')
          {
            System.out.println("Please try again.");
            monthChanges = 'm';
          }
        }

        //if user answers yes, application fills month with last months entry
        if(monthChanges == 'N' || monthChanges == 'n')
        {
          fullYear[monthCount].setIncome(fullYear[monthCount-1].getIncome());
          fullYear[monthCount].setRent(fullYear[monthCount-1].getRent());
          fullYear[monthCount].setCar(fullYear[monthCount-1].getCar());
          fullYear[monthCount].setGas(fullYear[monthCount-1].getGas());
          fullYear[monthCount].setFood(fullYear[monthCount-1].getFood());
        }
      }

      // this is the standard path followed by january, and by the other months by user request.
      if (monthCount <= 0 || monthChanges == 'Y' || monthChanges == 'y')
      {
        for(errorCode = -1 ; errorCode != 0; )
        {
          if (errorCode != -1)
          {
            System.out.println(fullYear[monthCount].getErrorMessage(errorCode));
          }
          System.out.print("Enter this month's income: $");
          fullYear[monthCount].setIncome(input.nextInt());
          errorCode = fullYear[monthCount].getErrorCode();
        }

        for(errorCode = -1 ; errorCode != 0; )
        {
          if (errorCode != -1)
          {
            System.out.println(fullYear[monthCount].getErrorMessage(errorCode));
          }
          System.out.print("Enter this month's rent: $");
          fullYear[monthCount].setRent(input.nextInt());
          errorCode = fullYear[monthCount].getErrorCode();
        }

        for(errorCode = -1 ; errorCode != 0; )
        {
          if (errorCode != -1)
          {
            System.out.println(fullYear[monthCount].getErrorMessage(errorCode));
          }
          System.out.print("Enter this month's car payment: $");
          fullYear[monthCount].setCar(input.nextInt());
          errorCode = fullYear[monthCount].getErrorCode();
        }

        for(errorCode = -1 ; errorCode != 0; )
        {
          if (errorCode != -1)
          {
            System.out.println(fullYear[monthCount].getErrorMessage(errorCode));
          }
          System.out.print("Enter this month's gas costs: $");
          fullYear[monthCount].setGas(input.nextInt());
          errorCode = fullYear[monthCount].getErrorCode();
        }

        for(errorCode = -1 ; errorCode != 0; )
        { 
          if (errorCode != -1)
          {
            System.out.println(fullYear[monthCount].getErrorMessage(errorCode));
          }
          System.out.print("Enter this month's food costs: $");
          fullYear[monthCount].setFood(input.nextInt());
          errorCode = fullYear[monthCount].getErrorCode();
        }
      }

      //display code
      //then, I retrieve the entered values from the object
      System.out.println(monthNames[monthCount]);
      System.out.println("Income:  $" + fullYear[monthCount].getIncome());
      System.out.println("Rent:    $" + fullYear[monthCount].getRent());
      System.out.println("Car:     $" + fullYear[monthCount].getCar());
      System.out.println("Gas:     $" + fullYear[monthCount].getGas());
      System.out.println("Food:    $" + fullYear[monthCount].getFood());
      System.out.println( "                                                      ");

      //then, I check that the object's derived values are correct
      System.out.println("Savings: $" + fullYear[monthCount].getSavings());
      System.out.println("Fun:     $" + fullYear[monthCount].getFun());
    
      //last, I check that the error message is correct
      // this line is for debugging
      //System.out.println("Error Message: " + fullYear[monthCount].getErrorCode());
      System.out.println( "******************************************************");
    }

    //everything after the data collection loop goes here
    
    System.out.printf("%-10s%-15s%-12s%-15s%-12s%-12s%-12s%-12s\n", "Month", "Income", "Rent", "Car Payment", "Gas Costs", "Food Costs", "Savings", "Fun");
    for(monthCount = 0; monthCount < monthTotal; monthCount++)
    {
      printRow(monthNames[monthCount], "$" + fullYear[monthCount].getIncome(), "$" + fullYear[monthCount].getRent(), "$" + fullYear[monthCount].getCar(), "$" + fullYear[monthCount].getGas(), "$" + fullYear[monthCount].getFood(), "$" + fullYear[monthCount].getSavings(), "$" + fullYear[monthCount].getFun());
    }

    System.out.println( "                                                      ");
    System.out.println( "******************************************************");

    // display the application farewell text
    System.out.println( "*               Thank you for using!                 *");
    System.out.println( "******************************************************");
    System.out.println( "                                                      ");
    input.close();
  }

  private static void printRow(String month, String income, String rent, String car, String gas, String food, String savings, String fun) 
  {
    System.out.printf("%-10s%-15s%-12s%-15s%-12s%-12s%-12s%-12s\n", month, income, rent, car, gas, food, savings, fun);
  }

}
