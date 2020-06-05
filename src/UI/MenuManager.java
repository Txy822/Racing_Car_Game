package UI;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileInputStream;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MenuManager {

  protected static double scl = 1;
  protected static Stage primaryStage;
  protected static String carColour = "Red";
  protected static int ID = 0;
  protected static String playSrc = "src/Resources/Button/playButton.png";
  protected static String leadSrc = "src/Resources/Button/leaderButton.png";
  protected static String insSrc = "src/Resources/Button/insButton.png";
  protected static String changeSrc = "src/Resources/Button/changeButton.png";
  protected static String exitSrc = "src/Resources/Button/exitButton.png";
  protected static String menuBGSrc = "src/Resources/BG/mainMenu.png";

  public MenuManager(Stage primaryStage, String carColour, int ID) {
    this.primaryStage = primaryStage;
    this.carColour = carColour;
    this.ID = ID;
  }

  public MenuManager(Stage primaryStage, String carColour) {
    this.primaryStage = primaryStage;
    this.carColour = carColour;
  }

  /**
   * when given a destination and the primaryStage, it will initialize the create method of the
   * destination
   */
  public static Scene navigator(Stage primaryStage, String destination) throws Exception {
    if (destination.equals("Game")) {
      return GameMenu.create(primaryStage);
    }
    if (destination.equals("Instructions")) {
      return Instruction.create(primaryStage);
    }
    if (destination.equals("Leaderboard")) {
      return Leaderboard.create(primaryStage);
    }
    if (destination.equals("Garage")) {
      return Garage.create(primaryStage);
    }
    return MenuManager.create(primaryStage);
  }

  /**
   * create method of MenumManager class
   */
  public static Scene create(Stage stage) throws Exception {
    double width = widthVal();
    double height = heightVal();
    //stage.setMaximized(true);

    BorderPane mainMenu = new BorderPane();
    Background bg = addBackground(menuBGSrc, width, height);

    VBox menuButton = addVBox(stage);

    mainMenu.setLeft(menuButton);
    mainMenu.setBackground(bg);

    return new Scene(mainMenu, width * scl, height * scl);
  }

  /**
   * Takes a string for the background source and return a background for the scene.
   * <p>
   * Best used on background of size 1920 x 1080
   *
   * @return Background
   */
  public static Background addBackground(String bgSrc, double width, double height)
      throws Exception {
    FileInputStream bg = new FileInputStream(bgSrc);
    Image bgImg = new Image(bg);

    BackgroundSize bSize = new BackgroundSize(width, height, false, false, true, false);

    Background background = new Background(new BackgroundImage(bgImg,
        BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT,
        BackgroundPosition.CENTER,
        bSize));

    return background;
  }

  /**
   * Get width Value of monitor
   * <p>
   * Use to return height at aspect ratio of 16:9 to scale properly to high DPI screens
   *
   * @return double
   */
  public static double widthVal() {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    return screenSize.getWidth();
  }

  public static double heightVal() {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    return screenSize.getHeight();
  }


  /**
   * UIClass specific only!
   * <p>
   * Create left shoulder buttons for the main menu,
   * <p>
   * If called on other classes, recommend to override the method.
   * <p>
   * Refer to the original method on UIClass for UI properties set.
   *
   * @return VBox
   */
  protected static VBox addVBox(Stage stage) throws Exception {
    VBox menu = new VBox(30);
    menu.setBackground(Background.EMPTY);
    menu.setPadding(new Insets(10));
    menu.setTranslateY(190);
    menu.setTranslateX(60);
    menu.setAlignment(Pos.CENTER_LEFT);
    //menu.setStyle("-fx-focus-color: transparent;");

    Button playButton = newButton(playSrc, 0.8);
    playButton.setOnAction(e -> {
      try {
        MenuManager ui = new MenuManager(primaryStage, carColour, ID);
        GameMenu gameMenu = new GameMenu(stage, ui);
        stage.setScene(gameMenu.create(stage));
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    });
    playButton.setTranslateX(150);

    Button leadButton = newButton(leadSrc, 0.8);
    leadButton.setTranslateX(108);
    leadButton.setOnAction(e -> {
      try {
        stage.setScene(Leaderboard.create(stage));
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    });

    HBox hbox = addHboxMain(stage);

    menu.getChildren().addAll(playButton, leadButton, hbox);

    return menu;
  }

  /**
   * Creates a generic Button method, creates a button with generic UI properties.
   * <p>
   * Returns a generic button.Recommended scale 0.8
   * <p>
   * If set, must use setOnAction to lead to another scene.
   *
   * @return Button
   */
  public static Button newButton(String fileSrc, double scl) throws Exception {

    FileInputStream fileG = new FileInputStream(fileSrc);
    Image img = new Image(fileG);
    ImageView imgView = new ImageView(img);
    imgView.setFitHeight(img.getHeight() * scl);
    imgView.setFitWidth(img.getWidth() * scl);
    imgView.setPreserveRatio(true);

    Button button = new Button("", imgView);
    button.setStyle("-fx-background-color: transparent");

    return button;
  }

  public static ImageView newImage(String fileSrc, int imgscl) throws Exception {
    FileInputStream fileG = new FileInputStream(fileSrc);
    Image img = new Image(fileG);

    //Setting the image view
    ImageView imgView = new ImageView(img);
    imgView.setFitHeight(img.getHeight() * imgscl);
    imgView.setFitWidth(img.getWidth() * imgscl);
    imgView.setPreserveRatio(true);

    return imgView;
  }

  /**
   * Returns the HBox node of MenuManager, of which contains all menu buttons
   *
   * @return HBox
   */
  protected static HBox addHboxMain(Stage stage) throws Exception {

    HBox hbox = new HBox();
    hbox.setPadding(new Insets(0, 12, 0, 12));
    hbox.setAlignment(Pos.BASELINE_LEFT);
    hbox.setTranslateX(20);

    Button changeButton = newButton(changeSrc, 0.8);
    changeButton.setTranslateX(600);
    changeButton.setOnAction(e -> {
      try {
        stage.setScene(Garage.create(stage, carColour));
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    });

    Button exit = newButton(exitSrc, 0.8);
    exit.setOnAction(e -> {
      try {
        System.exit(0);
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    });
    exit.setTranslateX(450);

    Button insButton = newButton(insSrc, 0.8);
    insButton.setTranslateX(30);
    insButton.setOnAction(e -> {
      try {
        stage.setScene(Instruction.create(stage));
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    });

    hbox.getChildren().addAll(insButton, exit, changeButton);

    return hbox;
  }

  /**
   * Generic bottom aligned button, however button creation not part of method.
   * <p>
   * Used primarily for back buttons, if method called must create button for this method.
   */
  public static HBox addHbox(Stage stage) throws Exception {

    HBox hbox = new HBox();
    hbox.setPadding(new Insets(0, 0, 0, 0));
    hbox.setAlignment(Pos.BASELINE_CENTER);
    hbox.setTranslateY(-170);
    hbox.setTranslateX(160);

    return hbox;
  }

  /**
   * return car colour of this class
   */
  public static String getCarCol() {
    return carColour;
  }

  /**
   * set car colour
   */
  protected static void setCarCol(String col) {
    carColour = col;
  }

  /**
   * get ID
   */
  public int getID() {
    return ID;
  }

  public Stage getStage() {
    return primaryStage;
  }

}




