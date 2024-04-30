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
import javafx.scene.text.Text;

/*
Update YearData class to handle checking of changing multiple categories
Show currency values, especially for savings, fun, total
Update data for textField after they've left it
Set month button should turn into the YearScene
Add "restart" button to YearScene. Should take you back to BudgetUI scene.
Make a "pop-up" class for exception handling
 - inserting data into objects
 - having a negative value for total
 - changing multiple categories

*/

public class Scene2 {
  // Headings
  private Label lblMonthName = new Label("[MONTH]");
  private Label lblHeadingOld = new Label("Last Month");
  private Label lblHeadingNew = new Label("Next Month");
  
  // Old column
  private Text txtOldMonthlyIncome = new Text();
  private Text txtOldRent = new Text();
  private Text txtOldCar = new Text();
  private Text txtOldGas = new Text();
  private Text txtOldFood = new Text();
  private Text txtOldPercent = new Text();
  private Text txtOldSavings = new Text();
  private Text txtOldFun = new Text();
  private Text txtOldTotal = new Text();
  
  // New column
  private TextField tfNewMonthlyIncome = new TextField();
  private TextField tfNewRent = new TextField();
  private TextField tfNewCar = new TextField();
  private TextField tfNewGas = new TextField();
  private TextField tfNewFood = new TextField();
  private TextField tfNewPercent = new TextField();
  private Text txtNewSavings = new Text();
  private Text txtNewFun = new Text();
  private Text txtNewTotal = new Text();
  
  // Buttons
  private Button btnSetMonth = new Button("Set Month");
  private Button btnExit = new Button("Exit");
  
  // Helper variables
  static final String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", 
                        "Aug", "Sep", "Oct", "Nov", "Dec"};
  private int currMonth = 1;
  private YearBudget yearData;
  private MonthBudget monthData;
  private Scene scene;

  public Scene2(Stage primaryStage, YearBudget yearData) {
    // Data from last scene. Should have January filled in
    this.yearData = yearData;
    
    // Create UI
    GridPane gridPane = new GridPane();
    gridPane.setGridLinesVisible(false);
    addNodes(gridPane);
    lblMonthName.setText(monthNames[currMonth]);

    // Set properties for UI
    setupProperties(gridPane);
    
    //StackPane root = new StackPane(textField, btn);
    scene = new Scene(gridPane, 400, 500);
    
    monthData = yearData.getMonth(0);
    populateOldData(monthData);
    populateNewData(monthData);

    // Event handler for updating the TextField
    btnSetMonth.setOnAction(e -> {
      //tfNewCar.setText("Button Click");
      monthData = getNewMonthData(currMonth);
      yearData.setMonth(currMonth, monthData);
      populateOldData(monthData);
      populateNewData(monthData);
      currMonth++;
      // if currMonth >= 12: open new scene, pass yearData. display table
      lblMonthName.setText(monthNames[currMonth]);
    });
    
    btnExit.setOnAction(e -> {
      for(; currMonth < 12; currMonth++){
        yearData.setMonth(currMonth, monthData);
      }
      YearScene yearScene = new YearScene(yearData);
      primaryStage.setScene(yearScene.getScene());
    });
  }

  public Scene getScene() {
      return scene;
  }
  
  public MonthBudget getNewMonthData(int currMonth){
    // Grab from text fields
    Double monthlyIncome = Double.parseDouble(tfNewMonthlyIncome.getText());
    Double rent = Double.parseDouble(tfNewRent.getText());
    Double car = Double.parseDouble(tfNewCar.getText());
    Double gas = Double.parseDouble(tfNewGas.getText());
    Double food = Double.parseDouble(tfNewFood.getText());
    Double percent = Double.parseDouble(tfNewPercent.getText());

    return new MonthBudget(currMonth, monthlyIncome, rent, car, gas, food, percent);
  }

  public void populateOldData(MonthBudget monthData) {
    // income, rent, car, gas, food, savings, fun, total
    double[] dataDouble = monthData.getAllValues();
    String[] dataString = new String[8];
    for(int i = 0; i < dataDouble.length; i++){
      String strNum = String.valueOf(dataDouble[i]);
      dataString[i] = strNum;
    }
    txtOldMonthlyIncome.setText(dataString[0]);
    txtOldRent.setText(dataString[1]);
    txtOldCar.setText(dataString[2]);
    txtOldGas.setText(dataString[3]);
    txtOldFood.setText(dataString[4]);
    txtOldPercent.setText(getCurrencyFromDouble(monthData.getPercentForSavings()));
    txtOldSavings.setText(String.valueOf(monthData.getSavings()));
    txtOldFun.setText(String.valueOf(monthData.getFun()));
    txtOldTotal.setText(String.valueOf(monthData.getTotal()));
  }
  
  public void populateNewData(MonthBudget monthData) {
    // income, rent, car, gas, food, savings, fun, total
    double[] dataDouble = monthData.getAllValues();
    String[] dataString = new String[8];
    for(int i = 0; i < dataDouble.length; i++){
      String strNum = String.valueOf(dataDouble[i]);
      dataString[i] = strNum;
    }
    tfNewMonthlyIncome.setText(dataString[0]);
    tfNewRent.setText(dataString[1]);
    tfNewCar.setText(dataString[2]);
    tfNewGas.setText(dataString[3]);
    tfNewFood.setText(dataString[4]);
    tfNewPercent.setText(String.valueOf(monthData.getPercentForSavings()));
  }
  
  public void addNodes(GridPane gridPane){
    int currRow = -1; // keep track of row position while adding nodes
    // Headings
    // btn.add(col, row, colspan, rowspan)
    gridPane.add(lblMonthName, 0, ++currRow, 3, 1);
    gridPane.add(lblHeadingOld, 1, ++currRow);
    gridPane.add(lblHeadingNew, 2, currRow);
    
    // Add old and new columns to gridpane
    gridPane.add(new Label("Monthly Income:"), 0, ++currRow);
    gridPane.add(txtOldMonthlyIncome, 1, currRow);
    gridPane.add(tfNewMonthlyIncome, 2, currRow);
    gridPane.add(new Label("Rent:"), 0, ++currRow);
    gridPane.add(txtOldRent, 1, currRow);
    gridPane.add(tfNewRent, 2, currRow);
    gridPane.add(new Label("Car:"), 0, ++currRow);
    gridPane.add(txtOldCar, 1, currRow);
    gridPane.add(tfNewCar, 2, currRow);
    gridPane.add(new Label("Gas:"), 0, ++currRow);
    gridPane.add(txtOldGas, 1, currRow);
    gridPane.add(tfNewGas, 2, currRow);
    gridPane.add(new Label("Food:"), 0, ++currRow);
    gridPane.add(txtOldFood, 1, currRow);
    gridPane.add(tfNewFood, 2, currRow);
    gridPane.add(new Label("Savings Percent:"), 0, ++currRow);
    gridPane.add(txtOldPercent, 1, currRow);
    gridPane.add(tfNewPercent, 2, currRow);
    gridPane.add(new Label("Savings:"), 0, ++currRow);
    gridPane.add(txtOldSavings, 1, currRow);
    gridPane.add(txtNewSavings, 2, currRow);
    gridPane.add(new Label("Fun:"), 0, ++currRow);
    gridPane.add(txtOldFun, 1, currRow);
    gridPane.add(txtNewFun, 2, currRow);
    gridPane.add(new Label("Total:"), 0, ++currRow);
    gridPane.add(txtOldTotal, 1, currRow);
    gridPane.add(txtNewTotal, 2, currRow);
    
    // Buttons
    gridPane.add(btnSetMonth, 0, ++currRow, 3, 1);
    gridPane.add(btnExit, 0, ++currRow, 3, 1);
  }
  
  public void setupProperties(GridPane gridPane){
    // General 
    gridPane.setHgap(5);
    gridPane.setVgap(5);
    gridPane.setAlignment(Pos.CENTER);
    
    // Headings
    GridPane.setHalignment(lblMonthName, HPos.CENTER);
    GridPane.setHalignment(lblHeadingNew, HPos.CENTER);
    GridPane.setHalignment(lblHeadingOld, HPos.CENTER);
    
    // Text
    GridPane.setHalignment(txtOldMonthlyIncome, HPos.CENTER);
    GridPane.setHalignment(txtOldRent, HPos.CENTER);
    GridPane.setHalignment(txtOldCar, HPos.CENTER);
    GridPane.setHalignment(txtOldGas, HPos.CENTER);
    GridPane.setHalignment(txtOldFood, HPos.CENTER);
    GridPane.setHalignment(txtOldPercent, HPos.CENTER);
    GridPane.setHalignment(txtOldSavings, HPos.CENTER);
    GridPane.setHalignment(txtOldFun, HPos.CENTER);
    GridPane.setHalignment(txtOldTotal, HPos.CENTER);
    
    // Buttons
    GridPane.setHalignment(btnSetMonth, HPos.CENTER);
    GridPane.setHalignment(btnExit, HPos.CENTER);
  }
  
  public String getCurrencyFromDouble(double num){
    String dataStr = String.format("%s%-10.2f", "$", num);
    return dataStr;
  }
}