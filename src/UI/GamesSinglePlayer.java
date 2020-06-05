package UI;

import GameModes.Arcarde;
import GameModes.TimeTrial;
import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GamesSinglePlayer {

  protected static double scl = 1;
  protected static String playSrc = "src/Resources/Button/playButton.png";
  protected static String gameBGSrc = "src/resources/BG/gameBG.png";
  protected static String backSrc = "src/resources/Button/backButton.png";
  protected static String arcadeSrc = "src/Resources/Button/arcadeButton.png";
  protected static String timeTrialSrc = "src/Resources/Button/timeTrialButton.png";
  private static Stage primaryStage;
  private static MenuManager ui;

  public GamesSinglePlayer(Stage primaryStage, MenuManager ui) {
    this.primaryStage = primaryStage;
    this.ui = ui;
  }

  /**
   * create method for single player menu
   */
  public static Scene create(Stage stage) throws Exception {

    BorderPane insScreen = new BorderPane();

    Background bg = ui.addBackground(gameBGSrc, widthVal(), heightVal());
    HBox exitButton = ui.addHbox(stage);

    VBox gameModes = new VBox(25);
    gameModes.setBackground(Background.EMPTY);
    gameModes.setTranslateY(50);
    gameModes.setTranslateX(-10);

    gameModes.setAlignment(Pos.TOP_LEFT);

    Button arcade = ui.newButton(arcadeSrc, 1);
    arcade.setOnAction(e -> {

      try {
        Arcarde arcadeScreen = new Arcarde(ui);
        arcadeScreen.start(stage);
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    });

    Button timeTrial = ui.newButton(timeTrialSrc, 1);

    timeTrial.setOnAction(e -> {
      try {
        TimeTrial trial = new TimeTrial(ui);
        trial.start(stage);
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    });

    gameModes.getChildren().addAll(arcade, timeTrial);

    Button exit = ui.newButton(backSrc, 0.8);
    exit.setOnAction(e -> {
      try {
        GameMenu gameMenu = new GameMenu(stage, ui);
        stage.setScene(gameMenu.create(stage));
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    });
    exitButton.getChildren().add(exit);
    exitButton.setTranslateY(-100);

    insScreen.setLeft(gameModes);
    insScreen.setBottom(exitButton);
    insScreen.setBackground(bg);

    Scene scene = new Scene(insScreen, widthVal(), heightVal());

    return scene;
  }

  /**
   * return width of screen
   */
  public static double widthVal() {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    return screenSize.getWidth();
  }

  /**
   * return height of screen
   */
  public static double heightVal() {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    return screenSize.getHeight();
  }

}
