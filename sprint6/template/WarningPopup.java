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
import javafx.stage.Modality;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;

public class WarningPopup {
  private Stage stage;
  private Scene scene;
  String message;
                        
  public WarningPopup(int error) {
    // Create Scene
    stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);
    GridPane gridPane = new GridPane();
    gridPane.setHgap(10);
    gridPane.setVgap(10);
    
    // Buttons that could be added for error
    Button btnOkay = new Button("Okay");
    btnOkay.setOnAction(e -> closeStage());
    GridPane.setHalignment(btnOkay, HPos.CENTER);
    
    // Set values for a given error
    switch(error){
      // Not double
      case 0:
        message = "Please make sure you are inputting positive numbers";
        gridPane.add(btnOkay, 0, 2);
        break;
      case 1:
        message = "Please enter a percentage between 0 and 100";
        gridPane.add(btnOkay, 0, 2);
        break;
      default:
        message = "Something went wrong but we don't know what exactly";
        gridPane.add(btnOkay, 0, 2);
    }
    
    // Did you just say womp womp?
    Text txtWomp = new Text("Womp Womp");
    txtWomp.setFont(new Font("Arial Bold", 25));
    gridPane.add(txtWomp,0,0);
    GridPane.setHalignment(txtWomp, HPos.CENTER);
    
    // Error message
    Label lblMessage = new Label(message);
    gridPane.add(lblMessage, 0, 1);
    scene = new Scene(gridPane, 350, 200);
    GridPane.setHalignment(lblMessage, HPos.CENTER);
    
    // Display the stage
    stage.setScene(scene);
    stage.setTitle("Error");
    stage.showAndWait();
  }

  public Scene getScene() {
      return scene;
  }
  
  private void closeStage(){
    stage.close();
  }
}