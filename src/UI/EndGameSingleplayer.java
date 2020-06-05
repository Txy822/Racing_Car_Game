package UI;

import Database.Login;
import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EndGameSingleplayer {

  //Buttons and Background filepath
  protected static String podBGSrc = "src/Resources/BG/PodiumSingBG.png";
  protected static String retrySrc = "src/Resources/Button/retryButton.png";

  //Names for cars
  protected static String red = "Red";
  protected static String blue = "Blue";
  protected static String green = "Green";
  protected static String yellow = "Yellow";

  //Car images filepath
  protected static String redCarSrc = "src/Resources/Cars/CarRed.png";
  protected static String blueCarSrc = "src/Resources/Cars/CarBlue.png";
  protected static String greenCarSrc = "src/Resources/Cars/CarGreen.png";
  protected static String yellowCarSrc = "src/Resources/Cars/CarYellow.png";

  private static MenuManager ui;//passing ui manager that contains car colour information
  private int playerId;
  private BorderPane insScreen;//the parent node to display with create method
  private String timer;
  private String username;

  public EndGameSingleplayer(MenuManager ui, String timer) {
    this.playerId = ui.getID();
    this.ui = ui;
    this.timer = timer;
    insScreen = new BorderPane();
    username = Login.getUserName();
  }

  /**
   * input a colour for the car to return the source filepath of said car colour
   *
   * @return String
   */
  static String returnCarSrc(String carValue) {
    if (carValue.equals(red)) {
      return redCarSrc;
    }

    if (carValue.equals(blue)) {
      return blueCarSrc;
    }

    if (carValue.equals(yellow)) {
      return yellowCarSrc;
    }

    if (carValue.equals(green)) {
      return greenCarSrc;
    }

    return redCarSrc;
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

  /**
   * Create method for EndGameSinglePlayer
   */
  public Scene create(Stage primaryStage) throws Exception {
    final VBox vbox = new VBox(30);
    vbox.setPadding(new Insets(10, 0, 0, 10));
    vbox.setTranslateX(-700);
    vbox.setTranslateY(170);

    Scene scene = new Scene(insScreen, widthVal(), heightVal());

    Text time = new Text(timer);
    time.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 80));
    time.setTranslateY(-100);

    assemblePodium();

    Button leaveButton = new Button();
    leaveButton.setOnAction(event -> {
      try {
        primaryStage.setScene(MenuManager.create(primaryStage));
      } catch (Exception e) {
        e.printStackTrace();
      }
    });

    Image image = new Image(getClass().getResourceAsStream("/Resources/Button/backButton.png"));
    ImageView imageView = new ImageView(image);
    imageView.setFitHeight(image.getHeight() * 0.8);
    imageView.setFitWidth(image.getWidth() * 0.8);
    imageView.setPreserveRatio(true);

    leaveButton.setStyle("-fx-background-color: none;\n"
        + "  -fx-border-color: none;");

    leaveButton.setGraphic(imageView);

    Button retry = ui.newButton(retrySrc, 0.8);
    retry.setOnAction(event -> {
      try {
        GamesSinglePlayer singlePlayer = new GamesSinglePlayer(primaryStage, ui);
        primaryStage.setScene(singlePlayer.create(primaryStage));
      } catch (Exception e) {
        e.printStackTrace();
      }
    });

    leaveButton.setGraphic(imageView);

    vbox.setAlignment(Pos.CENTER_RIGHT);
    vbox.getChildren().addAll(time, retry, leaveButton);

    Background bg = ui.addBackground(podBGSrc, widthVal(), heightVal());

    insScreen.setRight(vbox);
    insScreen.setBackground(bg);

    return scene;
  }

  /**
   * create a podium with the racer name and car
   */
  private void assemblePodium() throws Exception {
    VBox podium1 = podiumPos(playerId, returnCarSrc(ui.getCarCol()));
    podium1.setTranslateX(320);
    podium1.setTranslateY(-110);
    insScreen.setLeft(podium1);
  }

  /**
   * method podiumPos returns a text and the image of the car colour as a VBox node
   */
  private VBox podiumPos(int playerName, String carImgSrc) throws Exception {
    Text name = new Text(username + " ID:" + String.valueOf(playerName));
    name.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 40));
    ImageView place = ui.newImage(carImgSrc, 1);
    VBox vbox = new VBox(15);

    vbox.setAlignment(Pos.CENTER);
    vbox.getChildren().addAll(name, place);
    return vbox;
  }
}
