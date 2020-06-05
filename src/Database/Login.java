package Database;

import UI.MenuManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Login class for player
 */
public class Login extends Application {

  protected static double scl = 1;//for some reason intellji does not scale new scene properly and requires screen size to be scaled by 0.5
  protected static String loginBGSrc = "src/Resources/BG/CredentialBG.png";
  protected static String loginSrc = "src/Resources/Button/loginButton.png";
  protected static String signUpSrc = "src/Resources/Button/signupButton.png";
  static Stage stage = new Stage();
  private static int ID = 0;
  private static String userName;
  private int initialTime = 0;//initial time when registered
  private int initialRank = 1;
  private int initialTrackId = 1;
  private DatabaseControl dbControl = new DatabaseControl();

  /**
   * Get player ID
   *
   * @return player ID
   */
  public static int getID() {
    return Login.ID;
  }

  /**
   * Sets player ID
   *
   * @param ID player ID
   */
  public static void setID(int ID) {
    Login.ID = ID;
  }

  /**
   * Get username
   *
   * @return player username
   */
  public static String getUserName() {
    return userName;
  }

  /**
   * @param userName username
   */
  public static void setUserName(String userName) {
    Login.userName = userName;
  }

  /**
   * Override the super method
   *
   * @param primaryStage A variable of type stage
   * @throws Exception throws exception
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    double width = MenuManager.widthVal();
    double height = (width / 16) * 9;
    double ratio = width / 1920;

    if (width > 2500) {
      scl = 0.5;
    }

    Background bg = MenuManager.addBackground(loginBGSrc, width, height);

    stage = primaryStage;
    primaryStage.setTitle("Login");

    BorderPane loginMenu = new BorderPane();
    addUIControlsBorderPane(loginMenu);
    loginMenu.setBackground(bg);

    // Create a scene with registration form grid pane as the root node
    Scene scene = new Scene(loginMenu, 1920 * ratio * scl, 1080 * ratio * scl);
    // Set the scene in primary stage
    primaryStage.setScene(scene);
    //display the stage.
    primaryStage.show();
  }

  /**
   * Adds UI control
   *
   * @param pane Pane
   */
  private void addUIControlsBorderPane(BorderPane pane) throws Exception {

    double width = MenuManager.widthVal();

    VBox loginScreen = new VBox(80);
    loginScreen.setBackground(Background.EMPTY);
    loginScreen.setPadding(new Insets(10));
    loginScreen.setTranslateY(260);
    loginScreen.setTranslateX(200);
    loginScreen.setAlignment(Pos.CENTER_LEFT);

    HBox hbox = new HBox();
    hbox.setPadding(new Insets(15, 12, 15, 12));
    hbox.setAlignment(Pos.BASELINE_LEFT);
    hbox.setTranslateY(-30);
    hbox.setTranslateX(-18);

    // Add User Name  Text Field
    TextField usernameField = new TextField();
    usernameField.setPrefHeight(70);
    usernameField.setMaxWidth(600);
    usernameField.setTranslateX(220);
    usernameField.setFont(Font.font("Arial", FontWeight.BLACK, 30));
    usernameField.setStyle("-fx-background-color: transparent;");

    // Add Password Field
    PasswordField passwordField = new PasswordField();
    passwordField.setPrefHeight(70);
    passwordField.setMaxWidth(600);
    passwordField.setTranslateX(70);
    passwordField.setTranslateX(180);
    passwordField.setFont(Font.font(30));
    passwordField.setStyle("-fx-background-color: transparent;");

    /// add button to allow for registration for new player, it can be Label action listner
    Button register = MenuManager.newButton(signUpSrc, 0.8);
    register.setTranslateX(135);
    // Add  Log in button
    Button log_in = MenuManager.newButton(loginSrc, 0.8);
    log_in.setDefaultButton(true);
    //login action handler
    log_in.setOnAction(event -> {

      if (usernameField.getText().isEmpty()) {
        showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(), "Form Error!",
            "Please enter your name");
        return;
      }

      if (passwordField.getText().isEmpty()) {
        showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(), "Form Error!",
            "Please enter a password");
        return;
      }
      ID = dbControl.getPlayerDetail(usernameField.getText()).getID();
      if (!dbControl.isRegistered(ID, usernameField.getText(), passwordField.getText())) {
        showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(), "Form Error!",
            "New? Please create account!");
        register.setOnAction(new EventHandler<ActionEvent>() {
          //handle the event in this case call the Registration app
          @Override
          public void handle(ActionEvent e) {
            Registration register = new Registration();
            try {
              register.start(Login.stage);
            } catch (Exception ex) {
              ex.printStackTrace();
            }
          }
        });
      } else {

        try {
          ID = dbControl.getPlayerDetail(usernameField.getText()).getID();
          setID(ID);
          setUserName(usernameField.getText());
          if (!dbControl.isPlayerIDExistFromPlayersTime(ID)) {
            dbControl.insertPlayersTime(ID, initialTime, initialTrackId);
          }

          if (!dbControl.isPlayerIDExistFromLeaderBoard(ID)) {
            dbControl.insertTimingToLeaderBoard(ID, initialTime, initialTrackId, initialRank);
          }
          MenuManager ui = new MenuManager(stage, "Red", getID());
          stage.setScene(ui.create(Login.stage));
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    });
    register.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        Registration register = new Registration();
        try {
          register.start(Login.stage);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    });
    hbox.getChildren().addAll(log_in, register);
    loginScreen.getChildren().addAll(usernameField, passwordField, hbox);//to add
    pane.setLeft(loginScreen);
  }

  /**
   * shows messages to the window
   *
   * @param alertType a variable of alertType
   * @param owner A variable of type Window
   * @param title A variable of type String
   * @param message A variable of type String
   */
  private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.initOwner(owner);
    alert.show();
  }
}
