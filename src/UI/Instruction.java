package UI;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Instruction extends MenuManager {

  protected static double scl = 1;
  protected static String insBGSrc = "src/Resources/BG/instructionBG.png";
  protected static String backSrc = "src/Resources/Button/backButton.png";

  public Instruction(Stage primaryStage, String carColour) {
    super(primaryStage, carColour);
  }

  /**
   * create method for all UI elements of instruction scene
   */
  public static Scene create(Stage stage) throws Exception {

    double width = widthVal();
    double height = (width / 16) * 9;

    BorderPane insScreen = new BorderPane();

    Background bg = addBackground(insBGSrc, width, height);
    HBox exitButton = addHbox(stage);

    Button exit = newButton(backSrc, 0.8);
    exit.setOnAction(e -> {
      try {
        stage.setScene(MenuManager.create(stage));
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    });
    exitButton.getChildren().add(exit);
    ;

    insScreen.setBottom(exitButton);
    insScreen.setBackground(bg);

    return new Scene(insScreen, width * scl, height * scl);
  }

}

