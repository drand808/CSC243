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
  private TextField tfTotalPayment = new TextField();
  private Button btCalculate = new Button("Display");
  @Override
  public void start(Stage primaryStage) {
    
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
    gridPane.add(new Label("Total Payment:"), 0, ++currRow);
    gridPane.add(tfTotalPayment, 1, currRow);
    gridPane.add(btCalculate, 0, ++currRow, 2, 1);
    //GridPane.setRowSpan(btCalculate, 5);

    // Set properties for UI
    GridPane.setHalignment(lblWelcomeMsg, HPos.CENTER);
    gridPane.setAlignment(Pos.CENTER);
    tfMonthlyIncome.setAlignment(Pos.BOTTOM_RIGHT);
    tfRent.setAlignment(Pos.BOTTOM_RIGHT);
    tfCar.setAlignment(Pos.BOTTOM_RIGHT);
    tfGas.setAlignment(Pos.BOTTOM_RIGHT);
    tfTotalPayment.setAlignment(Pos.BOTTOM_RIGHT);
    tfGas.setEditable(true);
    tfTotalPayment.setEditable(true);
    GridPane.setHalignment(btCalculate, HPos.CENTER);

    // Create a scene and place it in the stage
    Scene scene = new Scene(gridPane, 400, 250);
    primaryStage.setTitle("Annual Budget"); // Set title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    // action event 
    EventHandler<ActionEvent> addRow = new EventHandler<ActionEvent>() { 
        public void handle(ActionEvent e) 
        { 
            gridPane.add(new Label("row added"), 0, 7); 
        } 
    }; 
    btCalculate.setOnAction(addRow(currRow));
  }

  public static void main(String[] args) {
    Application.launch(args);
  }

}
