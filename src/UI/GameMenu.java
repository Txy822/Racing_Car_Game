package UI;

import GameModes.Multiplayer.MultiplayerSceneManager;
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

public class GameMenu {

  protected static double scl = 1;
  protected static String playSrc = "src/Resources/Button/playButton.png";
  protected static String gameBGSrc = "src/resources/BG/gameBG.png";
  protected static String backSrc = "src/resources/Button/backButton.png";
  protected static String multiSrc = "src/Resources/Button/multiplayerButton.png";
  protected static String singleSrc = "src/Resources/Button/singleplayerButton.png";
  private static Stage primaryStage;
  private static MenuManager ui; //ui contains variable relating to name and car color to be passed onto relevant single player classes

  public GameMenu(Stage primaryStage, MenuManager ui) {
    this.primaryStage = primaryStage;
    this.ui = ui;
  }


  /**
   * Create method for GameMenu containing all node element
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

    Button multiplayer = ui.newButton(multiSrc, 1);
    multiplayer.setOnAction(e -> {

      MultiplayerSceneManager multiplayerSceneManager = new MultiplayerSceneManager(stage, ui);
      try {
        multiplayerSceneManager.show();

      } catch (Exception e1) {
        e1.printStackTrace();
      }
    });

    Button singleplayer = ui.newButton(singleSrc, 1);

    singleplayer.setOnAction(e -> {
      try {
        GamesSinglePlayer singlePlayer = new GamesSinglePlayer(stage, ui);
        stage.setScene(singlePlayer.create(stage));

      } catch (Exception e1) {
        e1.printStackTrace();
      }
    });

    gameModes.getChildren().addAll(multiplayer, singleplayer);

    Button exit = ui.newButton(backSrc, 0.8);
    exit.setOnAction(e -> {
      try {
        stage.setScene(ui.navigator(primaryStage, "UI"));
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
