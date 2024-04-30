import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent; 
import javafx.event.EventHandler;


public class BudgetUI extends Application {
  private Label lblWelcomeMsg = new Label("Welcome Message");
  private TextField tfMonthlyIncome = new TextField();
  private TextField tfRent = new TextField();
  private TextField tfCar = new TextField();
  private TextField tfGas = new TextField();
  private TextField tfFood = new TextField();
  private TextField tfPercent = new TextField();
  private TextField tfTest = new TextField();
  private Button btInitial = new Button("Display");
  private MonthBudget january = new MonthBudget(0,0,0,0,0,0,0); 
  
  @Override
  public void start(Stage primaryStage) {
    GridPane gridPane1 = setScene1Grid();
    Scene scene = new Scene(gridPane1, 400, 250);
    
    GridPane gridPane2 = setScene2Grid();
    Scene scene2 = new Scene(gridPane2, 400, 250);
    
    //getInitialData(primaryStage, scene, scene2, january);
    primaryStage.setTitle("Annual Budget"); // Set title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    //MonthBudget january = null;
    btInitial.setOnAction(e -> {
      // Get values from TextFields
      Double monthlyIncome = Double.parseDouble(tfMonthlyIncome.getText());
      Double rent = Double.parseDouble(tfRent.getText());
      Double car = Double.parseDouble(tfCar.getText());
      Double gas = Double.parseDouble(tfGas.getText());
      Double food = Double.parseDouble(tfFood.getText());
      Double percent = Double.parseDouble(tfPercent.getText());

      // Setup Expense array being put into month
      Expense[] expenses = new Expense[4];
      expenses[0] = new Expense("Rent", rent);
      expenses[1] = new Expense("Car", car);
      expenses[2] = new Expense("Gas", gas);
      expenses[3] = new Expense("Food", food);

      january.setIncome(monthlyIncome);
      january.setExpenses(expenses);
      january.setPercentageForSavings(percent);
      january.calculateDisposables();
      //primaryStage.close();
      
      //primaryStage.setScene(scene2);
      //primaryStage.show();
      YearBudget yearData = new YearBudget(january);
      Scene2 test = new Scene2(primaryStage, yearData);
      //test.populateData("Data from Scene 1");
      primaryStage.setScene(test.getScene());
      //Stage second = getNewStage();
      //second.show();
      //second.show();
    });
    
    
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
  
  public GridPane setScene1Grid(){
    // Create UI
    GridPane gridPane = new GridPane();
    gridPane.setGridLinesVisible(false);
    gridPane.setHgap(5);
    gridPane.setVgap(5);
    int currRow = -1;
    // btn.add(col, row, colspan, rowspan)
    gridPane.add(lblWelcomeMsg, 0, ++currRow, 2, 1);
    gridPane.add(new Label("Monthly Income:"), 0, ++currRow);
    gridPane.add(tfMonthlyIncome, 1, currRow);
    //currRow += 1;
    gridPane.add(new Label("Rent:"), 0, ++currRow);
    gridPane.add(tfRent, 1, currRow);
    gridPane.add(new Label("Car:"), 0, ++currRow);
    gridPane.add(tfCar, 1, currRow);
    gridPane.add(new Label("Gas:"), 0, ++currRow);
    gridPane.add(tfGas, 1, currRow);
    gridPane.add(new Label("Food:"), 0, ++currRow);
    gridPane.add(tfFood, 1, currRow);
    gridPane.add(new Label("Savings Percent:"), 0, ++currRow);
    gridPane.add(tfPercent, 1, currRow);
    gridPane.add(btInitial, 0, ++currRow, 2, 1);
    //GridPane.setRowSpan(btCalculate, 5);

    // Set properties for UI
    GridPane.setHalignment(lblWelcomeMsg, HPos.CENTER);
    gridPane.setAlignment(Pos.CENTER);
    tfMonthlyIncome.setAlignment(Pos.BOTTOM_RIGHT);
    tfRent.setAlignment(Pos.BOTTOM_RIGHT);
    tfCar.setAlignment(Pos.BOTTOM_RIGHT);
    tfGas.setAlignment(Pos.BOTTOM_RIGHT);
    tfFood.setAlignment(Pos.BOTTOM_RIGHT);
    tfPercent.setAlignment(Pos.BOTTOM_RIGHT);
    //tfGas.setEditable(true);
    GridPane.setHalignment(btInitial, HPos.CENTER);
    return gridPane;
  }
  
  public GridPane setScene2Grid(){
    // Create UI
    GridPane gridPane = new GridPane();
    gridPane.setGridLinesVisible(true);
    gridPane.setHgap(5);
    gridPane.setVgap(5);
    int currRow = -1;
    // btn.add(col, row, colspan, rowspan)
    gridPane.add(new Label("test:"), 0, ++currRow);
    gridPane.add(tfTest, 1, currRow);
    //currRow += 1;
    //GridPane.setRowSpan(btCalculate, 5);

    // Set properties for UI
    gridPane.setAlignment(Pos.CENTER);
    tfTest.setAlignment(Pos.BOTTOM_RIGHT);
    return gridPane;
  }
  
  public void getInitialData(Stage primaryStage, Scene scene, Scene scene2, MonthBudget january){
    primaryStage.setTitle("Annual Budget"); // Set title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    //MonthBudget january = null;
    btInitial.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
          // Get values from TextFields
          Double monthlyIncome = Double.parseDouble(tfMonthlyIncome.getText());
          Double rent = Double.parseDouble(tfRent.getText());
          Double car = Double.parseDouble(tfCar.getText());
          Double gas = Double.parseDouble(tfGas.getText());
          Double food = Double.parseDouble(tfFood.getText());

          // Display the values (You can replace this with any logic you want)
          Expense[] expenses = new Expense[4];
          expenses[0] = new Expense("Rent", rent);
          expenses[1] = new Expense("Car", car);
          expenses[2] = new Expense("Gas", gas);
          expenses[3] = new Expense("Food", food);
          january.setIncome(monthlyIncome);
          january.setExpenses(expenses);
          january.calculateDisposables();
          primaryStage.close();
          
          primaryStage.setScene(scene2);
          primaryStage.show();
          //Stage second = getNewStage();
          //second.show();
          //second.show();
      }
    });
  }
  
}