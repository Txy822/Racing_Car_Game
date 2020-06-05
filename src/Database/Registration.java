package Database;

import UI.MenuManager;
import javafx.application.Application;
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
 * Registration class
 */
public class Registration extends Application {

  protected static double scl = 1;//for some reason intellji does not scale new scene properly and requires screen size to be scaled by 0.5
  protected static String registBGSrc = "src/Resources/BG/RegisterBG.png";
  protected static String backSrc = "src/Resources/Button/backButton.png";
  protected static String signUpSrc = "src/Resources/Button/signupButton.png";
  static Stage stage = new Stage();

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

    Background bg = MenuManager.addBackground(registBGSrc, width, height);

    stage = primaryStage;
    primaryStage.setTitle("Register");

    BorderPane registMenu = new BorderPane();
    addUIControlsBorderPane(registMenu);
    registMenu.setBackground(bg);

    // Create a scene with registration form grid pane as the root node
    Scene scene = new Scene(registMenu, 1920 * ratio * scl, 1080 * ratio * scl);
    // Set the scene in primary stage
    primaryStage.setScene(scene);

    primaryStage.show();
  }

  /**
   * Adds UI control
   *
   * @param pane Pane
   */
  private void addUIControlsBorderPane(BorderPane pane) throws Exception {
    double width = MenuManager.widthVal();

    VBox loginScreen = new VBox(100);
    loginScreen.setBackground(Background.EMPTY);
    loginScreen.setPadding(new Insets(10));
    loginScreen.setTranslateY(58);
    loginScreen.setTranslateX(200);
    loginScreen.setAlignment(Pos.CENTER_LEFT);

    HBox hbox = new HBox();
    hbox.setPadding(new Insets(15, 12, 15, 12));
    hbox.setAlignment(Pos.BASELINE_LEFT);
    hbox.setTranslateY(-30);
    hbox.setTranslateX(-18);

    // Add  user name   Text Field
    TextField usernameField = new TextField();
    usernameField.setPrefHeight(70);
    usernameField.setMaxWidth(600);
    usernameField.setTranslateX(360);
    usernameField.setFont(Font.font("Arial", FontWeight.BLACK, 30));
    usernameField.setStyle("-fx-background-color: transparent;");

    // Add  email Field
    TextField emailIDField = new TextField();
    emailIDField.setPrefHeight(70);
    emailIDField.setMaxWidth(600);
    emailIDField.setTranslateX(290);
    emailIDField.setTranslateY(17);
    emailIDField.setFont(Font.font("Arial", FontWeight.BLACK, 30));
    emailIDField.setStyle("-fx-background-color: transparent;");

    // Add Password Field
    PasswordField passwordField = new PasswordField();
    passwordField.setPrefHeight(70);
    passwordField.setMaxWidth(600);
    passwordField.setTranslateX(230);
    passwordField.setTranslateY(25);
    passwordField.setFont(Font.font(30));
    passwordField.setStyle("-fx-background-color: transparent;");

    // Add Password Field
    PasswordField passwordField2 = new PasswordField();
    passwordField2.setPrefHeight(70);
    passwordField2.setMaxWidth(600);
    passwordField2.setTranslateX(170);
    passwordField2.setTranslateY(32);
    passwordField2.setFont(Font.font(30));
    passwordField2.setStyle("-fx-background-color: transparent;" +
        "    -fx-background-insets: 0, 0 0 1 0 ;" +
        "    -fx-background-radius: 0 ;");

    // add button for event of sign in option for existing player
    Button button = MenuManager.newButton(backSrc, 0.8);

    // Add register  Button, it can be Label  event as mouse click
    Button register = MenuManager.newButton(signUpSrc, 0.8);
    register.setDefaultButton(true);
    register.setTranslateX(300);

    register.setOnAction(event -> {
      // int  playerID = Integer.parseInt(playerIDField.getText());
      String playerUserName = usernameField.getText();
      String playerEmail = emailIDField.getText();
      String playerPassword = passwordField.getText();
      DatabaseControl dbControl = new DatabaseControl();
      int ID = dbControl.getPlayerDetail(usernameField.getText()).getID();

      if (usernameField.getText().isEmpty()) {
        showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(), "Form Error!",
            "Please enter user name");
        return;
      }

      if (emailIDField.getText().isEmpty()) {
        showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(), "Form Error!",
            "Please enter your email id");
        return;
      }

      if (passwordField.getText().isEmpty()) {
        showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(), "Form Error!",
            "Please enter a password");
        return;
      }
      if (passwordField2.getText().isEmpty()) {
        showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(), "Form Error!",
            "Please re-enter a password");
        return;
      }
      if (!passwordField.getText().equals(passwordField2.getText())) {
        showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(), "Form Error!",
            "The password do not match");
        return;
      }
      if (new DatabaseControl()
          .isRegistered(ID, usernameField.getText(), passwordField.getText())) {
        showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(), "Form Error!",
            "This Detail exist! Please sign in");

        button.setOnAction(e -> {
          try {
            new Login().start(Registration.stage);
          } catch (Exception ex) {
            ex.printStackTrace();
          }

        });
        return;
      } else {
        try {

          String hashedPassword = HashPassword.generatePasswordHash(playerPassword);
          dbControl.insertPlayerDetail(playerUserName, playerEmail, hashedPassword);
          new Login().start(Registration.stage);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    });
    button.setOnAction(e -> {
      try {
        new Login().start(Registration.stage);
      } catch (Exception ex) {
        ex.printStackTrace();
      }

    });
    hbox.getChildren().addAll(button, register);
    loginScreen.getChildren()
        .addAll(usernameField, emailIDField, passwordField, passwordField2, hbox);//to add
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
