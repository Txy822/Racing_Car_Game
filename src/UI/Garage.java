package UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Garage extends MenuManager {

  //Scale val to scale node elements
  protected static double scl = 1;

  //Background and buttons filepath
  protected static String insBGSrc = "src/Resources/BG/GarageBG.png";
  protected static String backSrc = "src/Resources/Button/backButton.png";
  protected static String leftSrc = "src/Resources/Button/leftButton.png";
  protected static String rightSrc = "src/Resources/Button/rightButton.png";
  protected static String resetSrc = "src/Resources/Button/resetButton.png";
  protected static String saveSrc = "src/Resources/Button/saveButton.png";

  //Car images filepath
  protected static String redCarSrc = "src/Resources/Cars/CarRed.png";
  protected static String blueCarSrc = "src/Resources/Cars/CarBlue.png";
  protected static String greenCarSrc = "src/Resources/Cars/CarGreen.png";
  protected static String yellowCarSrc = "src/Resources/Cars/CarYellow.png";

  //Names for cars
  protected static String red = "Red";
  protected static String blue = "Blue";
  protected static String green = "Green";
  protected static String yellow = "Yellow";

  public Garage(Stage primaryStage, String carColour) {
    super(primaryStage, carColour);
  }


  /**
   * Create scene for Garage.class, this class has 5 buttons:
   * <p>
   * Save, reset, left car, right car and back buttons.
   * <p>
   * Details on what the buttons do are found in addUIControl Method
   */
  public static Scene create(Stage stage, String carValue) throws Exception {

    double width = widthVal();
    double height = (width / 16) * 9;

    BorderPane insScreen = new BorderPane();

    addUIControl(stage, insScreen, carValue);

    return new Scene(insScreen, width * scl, height * scl);
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
   * input a car colour to return its name
   *
   * @return String
   */
  static String getCarName(String carValue) {
    if (carValue.equals(red)) {
      return "Fiery Red";
    }

    if (carValue.equals(blue)) {
      return "Deep Blue";
    }

    if (carValue.equals(yellow)) {
      return "Golden Wind";
    }

    if (carValue.equals(green)) {
      return "Emerald Flash";
    }

    return redCarSrc;
  }

  /**
   * This method returns Garage.class UI node element through BorderPane object.
   * <p>
   *
   * @param stage is used to call this class again every time a new car is choosen
   * @param pane is used to add all node elements in this class
   * @param currentCar is used to reference the current car in the garage
   */
  private static void addUIControl(Stage stage, BorderPane pane, String currentCar)
      throws Exception {

    //UI for vertical buttons save and reset

    VBox garageScreen = new VBox(50);
    garageScreen.setBackground(Background.EMPTY);
    garageScreen.setPadding(new Insets(10));
    garageScreen.setTranslateY(100);
    garageScreen.setTranslateX(150);
    garageScreen.setAlignment(Pos.CENTER_LEFT);

    // Text object to return the name of the car colour and display on screen
    Text carName = new Text(getCarName(currentCar));
    carName.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 70));
    carName.setTranslateX(130);

    // Button object for save and reset button
    Button saveButton = newButton(saveSrc, 0.8);
    saveButton.setTranslateX(50);
    Button resetButton = newButton(resetSrc, 0.8);

    //store the updated car colour in the UIClass for other classes to reference
    saveButton.setOnAction(event -> {
      setCarCol(currentCar);
    });

    //returns the car colour to the default value, Red
    resetButton.setOnAction(event -> {
      setCarCol(red);
      try {
        stage.setScene(Garage.create(stage, red));
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    });

    //stacks 3 nodes by order of label,save button and resetButton
    garageScreen.getChildren().addAll(carName, saveButton, resetButton);

    //UI for horizontal buttons left,right and car image

    HBox hbox = new HBox();
    hbox.setPadding(new Insets(50, 50, 50, 50));
    hbox.setAlignment(Pos.BASELINE_CENTER);
    hbox.setTranslateY(-110);
    hbox.setTranslateX(-510);

    //Create and display the Car Image of the currentCar
    String carSrc = returnCarSrc(currentCar);
    ImageView car = newImage(carSrc, 1);
    car.setTranslateY(250);

    //create left and right Button, which when clicked calls Garage.create() method with the car colour
    Button leftButton = newButton(leftSrc, 0.8);
    leftButton.setTranslateX(-50);

    Button rightButton = newButton(rightSrc, 0.8);
    rightButton.setTranslateX(50);

    leftButton.setOnAction(event -> {
      if (currentCar.equals(red)) {
        try {
          stage.setScene(Garage.create(stage, green));
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
      if (currentCar.equals(blue)) {
        try {
          stage.setScene(Garage.create(stage, red));
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
      if (currentCar.equals(yellow)) {
        try {
          stage.setScene(Garage.create(stage, blue));
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
      if (currentCar.equals(green)) {
        try {
          stage.setScene(Garage.create(stage, yellow));
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
    });

    rightButton.setOnAction(event -> {
      if (currentCar.equals(red)) {
        try {
          stage.setScene(Garage.create(stage, blue));
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
      if (currentCar.equals(blue)) {
        try {
          stage.setScene(Garage.create(stage, yellow));
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
      if (currentCar.equals(yellow)) {
        try {
          stage.setScene(Garage.create(stage, green));
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
      if (currentCar.equals(green)) {
        try {
          stage.setScene(Garage.create(stage, red));
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
    });

    //stacks from left to right the left button, the car image, and the right button
    hbox.getChildren().addAll(leftButton, car, rightButton);

    //UI for background and back button
    double width = widthVal();
    double height = (width / 16) * 9;

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

    //bringing all the node elements together
    pane.setLeft(garageScreen);
    pane.setRight(hbox);
    pane.setBottom(exitButton);
    pane.setBackground(bg);
  }
}
