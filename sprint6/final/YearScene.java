import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.ActionEvent; 
import javafx.event.EventHandler;
import javafx.scene.text.Text;
import java.text.NumberFormat;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.Node;
import javafx.scene.control.Control;

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
    gridPane.setAlignment(Pos.CENTER);
    
    gridPane.setStyle("-fx-hgap: 1; -fx-vgap: 1; -fx-grid-lines-visible: true; -fx-hgrid-lines-visible: false; -fx-vgrid-lines-visible: true; -fx-grid-line-color: black");
    // linear-gradient(from 10% 30% to 23% 30%, repeat, white, gray);
    gridPane.setSnapToPixel(true);

    int row = 0;
    // Print month names
    for(int month = 0, col = 1; month < 12; month++, col++){
      Text text = new Text(monthNames[month]);
      text.setFont(Font.font("System", FontWeight.BOLD, 18));
      gridPane.add(text, col, row);
    }
    
    // Print data
    row++;
    for(int category = 0; category < categoryNames.length; category++, row++){
      //System.out.print(categoryNames[category] + "\t");
      gridPane.add(new Text(categoryNames[category]), 0, row);
      for(int month = 0, col = 1; month < 12; month++, col++){
        double data = yearData.getMonth(month).getAllValues()[category];
        String dataStr = String.format("%s%-10.2f", " $", data);
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
    scene = new Scene(gridPane, 1000, 200);
  }

  public Scene getScene() {
      return scene;
  }
}