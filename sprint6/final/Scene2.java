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
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
/*
Update YearData class to handle checking of changing multiple categories
 - make the textfield uneditable
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
  //lblMonthName.setStyle("-fx-font-weight: bold;");
  private Label lblHeadingOld = new Label("Last Month");
  private Label lblHeadingNew = new Label("Next Month");
  private Label lblOverIncome = new Label();
  
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
  private Button btnSetYear = new Button("Set Year");
  
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
      monthData = getNewMonthData(currMonth);
      yearData.setMonth(currMonth, monthData);
      populateOldData(monthData);
      populateNewData(monthData);
      changeUsedCategories();
      
      currMonth++;
      if(currMonth == 12){
        YearScene yearScene = new YearScene(primaryStage, yearData);
        primaryStage.setScene(yearScene.getScene());
        return;
      }
      if(allCategoriesChanged()){
        for(; currMonth < 12; currMonth++){
        yearData.setMonth(currMonth, monthData);
        }
        YearScene yearScene = new YearScene(primaryStage, yearData);
        primaryStage.setScene(yearScene.getScene());
        return;
      }
      lblMonthName.setText(monthNames[currMonth]);
    });
    
    btnSetYear.setOnAction(e -> {
      monthData = getNewMonthData(currMonth);
      yearData.setMonth(currMonth, monthData);
      currMonth++;
      for(; currMonth < 12; currMonth++){
        yearData.setMonth(currMonth, monthData);
      }
      YearScene yearScene = new YearScene(primaryStage, yearData);
      primaryStage.setScene(yearScene.getScene());
    });
  }
  
  public boolean checkInputIsNegative(double... variables){
    for(double var : variables){
      if (var < 0){
        return true;
      }
    }
    return false;
  }

  public Scene getScene() {
      return scene;
  }
  
  public MonthBudget getNewMonthData(int currMonth){
    Double monthlyIncome = 0.0, rent = 0.0, car = 0.0, gas = 0.0, food = 0.0, percent = 0.0;
    int badCategory = 0;
    try {
      // Grab from text fields
      monthlyIncome = Double.parseDouble(tfNewMonthlyIncome.getText());
      badCategory++;
      rent = Double.parseDouble(tfNewRent.getText());
      badCategory++;
      car = Double.parseDouble(tfNewCar.getText());
      badCategory++;
      gas = Double.parseDouble(tfNewGas.getText());
      badCategory++;
      food = Double.parseDouble(tfNewFood.getText());
      badCategory++;
      percent = Double.parseDouble(tfNewPercent.getText());
      badCategory++;
    }
    
    catch(Exception ex){
      System.out.println("> ERROR: Please enter a double");
      WarningPopup doubleError = new WarningPopup(0);
      switch(badCategory){
        case 0:
          tfNewMonthlyIncome.setText("0");
          break;
        case 1:
          tfNewRent.setText("0");
          break;
        case 2:
          tfNewCar.setText("0");
          break;
        case 3:
          tfNewGas.setText("0");
          break;
        case 4:
          tfNewFood.setText("0");
          break;
        case 5:
          tfNewPercent.setText("0");
          break;
      }
      return monthData;
    }
    
    double[] tfData = {monthlyIncome, rent, car, gas, food};
    for(int i = 0; i < tfData.length; i++){
      if(checkInputIsNegative(tfData[i])){
        switch(i){
          case 0:
            tfNewMonthlyIncome.setText("0");
            break;
          case 1:
            tfNewRent.setText("0");
            break;
          case 2:
            tfNewCar.setText("0");
            break;
          case 3:
            tfNewGas.setText("0");
            break;
          case 4:
            tfNewFood.setText("0");
            break;
          case 5:
            tfNewPercent.setText("0");
            break;
        }
        System.out.println("> ERROR: Please enter positive numbers");
        WarningPopup doubleError = new WarningPopup(0);
        return monthData;
      }        
    }
    
    // Check percentage
    if(percent > 100 || percent < 0){
      System.out.println("> ERROR: Please enter a percentage between 0 and 100");
      tfNewPercent.setText("0");
      WarningPopup doubleError = new WarningPopup(1);
      return monthData;
    }
    
    return new MonthBudget(currMonth, monthlyIncome, rent, car, gas, food, percent);
  }

  public void populateOldData(MonthBudget monthData) {
    // income, rent, car, gas, food, savings, fun, total
    double[] dataDouble = monthData.getAllValues();
    String[] dataString = new String[8];
    for(int i = 0; i < dataDouble.length; i++){
      String strNum = getCurrencyFromDouble(dataDouble[i]);
      dataString[i] = strNum;
    }
    txtOldMonthlyIncome.setText(dataString[0]);
    txtOldRent.setText(dataString[1]);
    txtOldCar.setText(dataString[2]);
    txtOldGas.setText(dataString[3]);
    txtOldFood.setText(dataString[4]);
    String dataStr = String.format("%.2f%%", monthData.getPercentForSavings());
    txtOldPercent.setText(dataStr);
    txtOldSavings.setText(dataString[5]);
    txtOldFun.setText(dataString[6]);
    txtOldTotal.setText(dataString[7]);
  }
  
  public void populateNewData(MonthBudget monthData) {
    // income, rent, car, gas, food, savings, fun, total
    double[] dataDouble = monthData.getAllValues();
    String[] dataString = new String[8];
    for(int i = 0; i < dataDouble.length; i++){
      //String strNum = String.valueOf(dataDouble[i]);
      String strNum = getTextfieldFormatFromDouble(dataDouble[i]);
      dataString[i] = strNum;
    }
    tfNewMonthlyIncome.setText(dataString[0]);
    tfNewRent.setText(dataString[1]);
    tfNewCar.setText(dataString[2]);
    tfNewGas.setText(dataString[3]);
    tfNewFood.setText(dataString[4]);
    tfNewPercent.setText(getTextfieldFormatFromDouble(monthData.getPercentForSavings()));
    populateNewDisposable();
  }
  
  public void addNodes(GridPane gridPane){
    int currRow = -1; // keep track of row position while adding nodes
    
    // Headings
    // btn.add(col, row, colspan, rowspan)
    gridPane.add(lblMonthName, 0, ++currRow, 3, 1);
    gridPane.add(lblHeadingOld, 1, ++currRow);
    gridPane.add(lblHeadingNew, 2, currRow);
    
    // Old and new columns
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
    gridPane.add(lblOverIncome, 0, ++currRow, 3, 1);
    
    // Focus listeners - update values after users clicks out of a textfield
    addFocusListener(tfNewMonthlyIncome);
    addFocusListener(tfNewRent);
    addFocusListener(tfNewCar);
    addFocusListener(tfNewGas);
    addFocusListener(tfNewFood);
    addFocusListener(tfNewPercent);
    
    // Buttons
    gridPane.add(btnSetMonth, 0, ++currRow, 3, 1);
    gridPane.add(btnSetYear, 0, ++currRow, 3, 1);
  }
  
  public void addFocusListener(TextField textField) {
    textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
      // Focus lost
      if (!newValue) {
          populateNewDisposable();
      }
    });
  }
  
  public void populateNewDisposable(){
    monthData = getNewMonthData(currMonth);
    txtNewSavings.setText(getCurrencyFromDouble(monthData.getSavings()));
    txtNewFun.setText(getCurrencyFromDouble(monthData.getFun()));
    txtNewTotal.setText(getCurrencyFromDouble(monthData.getTotal()));
    checkOverIncome(monthData);
  }
  
  public void checkOverIncome(MonthBudget monthData){
    double income = monthData.getIncome();
    double total = monthData.getTotal();
    if (income < total){
      System.out.println("> WARNING: current costs exceed salary");
      lblOverIncome.setText("WARNING: current costs exceed salary");
    }
    else {
      lblOverIncome.setText("");
    }
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
    
    // Labels
    lblOverIncome.setFont(new Font("Arial Bold", 15));
    lblOverIncome.setTextFill(Color.color(1,0,0));
    
    // Buttons
    GridPane.setHalignment(btnSetMonth, HPos.CENTER);
    GridPane.setHalignment(btnSetYear, HPos.CENTER);
  }
  
  public String getCurrencyFromDouble(double num){
    String dataStr = String.format("%s%.2f", "$", num);
    return dataStr;
  }
  
  public String getTextfieldFormatFromDouble(double num){
    String dataStr = String.format("%.2f", num);
    return dataStr;
  }
  
  public void changeUsedCategories(){
    TextField[] categoryTextFields = new TextField[5];
    categoryTextFields[0] = tfNewMonthlyIncome;
    categoryTextFields[1] = tfNewRent;
    categoryTextFields[2] = tfNewCar;
    categoryTextFields[3] = tfNewGas;
    categoryTextFields[4] = tfNewFood;
    
    // income, rent, car, gas, food, savings, fun, total
    double[] oldMonthDataArr = yearData.getMonth(currMonth-1).getAllValues();
    double[] newMonthData = monthData.getAllValues();
    
    // Check expense categories
    for(int category = 0; category < oldMonthDataArr.length-3; category++){
      // this category was changed
      if(oldMonthDataArr[category] != newMonthData[category]){
        System.out.println("Category changed at idx: " + category);
        categoryTextFields[category].setEditable(false);
      }
    }
    
    // Check percentage
    if(yearData.getMonth(currMonth-1).getPercentForSavings() !=
        monthData.getPercentForSavings()){
      tfNewPercent.setEditable(false);
    }
  }
  
  public boolean allCategoriesChanged(){
    TextField[] categoryTextFields = new TextField[6];
    categoryTextFields[0] = tfNewMonthlyIncome;
    categoryTextFields[1] = tfNewRent;
    categoryTextFields[2] = tfNewCar;
    categoryTextFields[3] = tfNewGas;
    categoryTextFields[4] = tfNewFood;
    categoryTextFields[5] = tfNewPercent;
    
    for(int category = 0; category < categoryTextFields.length; category++){
      if(categoryTextFields[category].isEditable()){
        return false;
      }
    }
    return true;
  }
}