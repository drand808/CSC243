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

public class YearScene {
    private int currMonth = 1;
    private YearBudget yearData;
    private MonthBudget monthData;
    private Scene scene;
    private Label lblMonthName = new Label("[MONTH]");
    private Label lblHeadingOld = new Label("Last Month");
    private Label lblHeadingNew = new Label("Next Month");
    private TextField tfOldMonthlyIncome = new TextField();
    private TextField tfOldRent = new TextField();
    private TextField tfOldCar = new TextField();
    private TextField tfOldGas = new TextField();
    private TextField tfOldFood = new TextField();
    private TextField tfOldPercent = new TextField();
    private TextField tfNewMonthlyIncome = new TextField();
    private TextField tfNewRent = new TextField();
    private TextField tfNewCar = new TextField();
    private TextField tfNewGas = new TextField();
    private TextField tfNewFood = new TextField();
    private TextField tfNewPercent = new TextField();
    private Button btnSetMonth = new Button("Set Month");
    private Button btnExit = new Button("Exit");
    static final String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", 
                          "Aug", "Sep", "Oct", "Nov", "Dec"};

    public YearScene(YearBudget yearData) {
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
      scene = new Scene(gridPane, 500, 300);
      
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
        System.out.println(strNum);
        dataString[i] = strNum;
      }
      tfOldMonthlyIncome.setText(dataString[0]);
      tfOldRent.setText(dataString[1]);
      tfOldCar.setText(dataString[2]);
      tfOldGas.setText(dataString[3]);
      tfOldFood.setText(dataString[4]);
      tfOldPercent.setText(String.valueOf(monthData.getPercentForSavings()));
    }
    
    public void populateNewData(MonthBudget monthData) {
      // income, rent, car, gas, food, savings, fun, total
      double[] dataDouble = monthData.getAllValues();
      String[] dataString = new String[8];
      for(int i = 0; i < dataDouble.length; i++){
        String strNum = String.valueOf(dataDouble[i]);
        System.out.println(strNum);
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
      
      // Text fields
      gridPane.add(new Label("Monthly Income:"), 0, ++currRow);
      gridPane.add(tfOldMonthlyIncome, 1, currRow);
      gridPane.add(tfNewMonthlyIncome, 2, currRow);
      gridPane.add(new Label("Rent:"), 0, ++currRow);
      gridPane.add(tfOldRent, 1, currRow);
      gridPane.add(tfNewRent, 2, currRow);
      gridPane.add(new Label("Car:"), 0, ++currRow);
      gridPane.add(tfOldCar, 1, currRow);
      gridPane.add(tfNewCar, 2, currRow);
      gridPane.add(new Label("Gas:"), 0, ++currRow);
      gridPane.add(tfOldGas, 1, currRow);
      gridPane.add(tfNewGas, 2, currRow);
      gridPane.add(new Label("Food:"), 0, ++currRow);
      gridPane.add(tfOldFood, 1, currRow);
      gridPane.add(tfNewFood, 2, currRow);
      gridPane.add(new Label("Savings Percent:"), 0, ++currRow);
      gridPane.add(tfOldPercent, 1, currRow);
      gridPane.add(tfNewPercent, 2, currRow);
      
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
      
      // Text fields
      tfOldMonthlyIncome.setAlignment(Pos.BOTTOM_RIGHT);
      tfOldRent.setAlignment(Pos.BOTTOM_RIGHT);
      tfOldCar.setAlignment(Pos.BOTTOM_RIGHT);
      tfOldGas.setAlignment(Pos.BOTTOM_RIGHT);
      tfOldFood.setAlignment(Pos.BOTTOM_RIGHT);
      tfOldPercent.setAlignment(Pos.BOTTOM_RIGHT);
      
      // Old textfields have last month data, user cannot edit
      tfOldMonthlyIncome.setEditable(false);
      tfOldRent.setEditable(false);
      tfOldCar.setEditable(false);
      tfOldGas.setEditable(false);
      tfOldFood.setEditable(false);
      tfOldPercent.setEditable(false);
      
      // Buttons
      GridPane.setHalignment(btnSetMonth, HPos.CENTER);
      GridPane.setHalignment(btnExit, HPos.CENTER);
    }
}