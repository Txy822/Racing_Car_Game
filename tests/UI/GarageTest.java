package UI;

import static org.junit.Assert.assertEquals;

import javafx.stage.Stage;
import org.junit.Test;

class GarageTest {

  @Test
  void returnCarSrc() {
    Stage primaryStage = new Stage();
    Garage garage = new Garage(primaryStage, "Red");
    assertEquals(garage.returnCarSrc("Red"), "src/Resources/Cars/CarRed.png");
  }

  @Test
  void getCarName() {
    Stage primaryStage = new Stage();
    Garage garage = new Garage(primaryStage, "Red");
    assertEquals(garage.returnCarSrc("Red"), "Fiery Red");
  }
}