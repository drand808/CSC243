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
import java.text.NumberFormat;
import javafx.scene.text.Font;

public class YearScene {
  private Scene scene;
  private YearBudget yearData;
  private Button btnRestart = new Button("Restart");
  
  static final String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", 
                        "Aug", "Sep", "Oct", "Nov", "Dec"};
  static final String[] categoryNames = {"Salary", "Rent", "Car", "Gas", "Food",
                            "Savings", "Fun", "Total"};
                        
  public YearScene(Stage primaryStage, YearBudget yearData) {
    // Data from last scene. Should have January filled in
    this.yearData = yearData;
    
    GridPane gridPane = new GridPane();
    gridPane.setHgap(10);
    gridPane.setVgap(10);
    gridPane.setGridLinesVisible(false);
    gridPane.add(new Text("Categories"), 0, 0);
    
    int row = 0;
    // Print month names
    for(int month = 0, col = 1; month < 12; month++, col++){
      Text text = new Text(monthNames[month]);
      gridPane.add(text, col, row);
    }
    
    // Print data
    row++;
    for(int category = 0; category < categoryNames.length; category++, row++){
      //System.out.print(categoryNames[category] + "\t");
      gridPane.add(new Text(categoryNames[category]), 0, row);
      for(int month = 0, col = 1; month < 12; month++, col++){
        double data = yearData.getMonth(month).getAllValues()[category];
        String dataStr = String.format("%s%-10.2f", "$", data);
        gridPane.add(new Text(dataStr), col, row);
      }
    }
    
    // Add restart
    gridPane.add(btnRestart, 0, ++row, 13, 1);
    GridPane.setHalignment(btnRestart, HPos.CENTER);
    btnRestart.setOnAction(e -> {
      primaryStage.close();
      BudgetUI budgetUI = new BudgetUI();
      budgetUI.start(primaryStage);
      //primaryStage.setScene(yearScene.getScene());
    });

    // Create Scene
    scene = new Scene(gridPane, 1300, 300);
  }

  public Scene getScene() {
      return scene;
  }
}