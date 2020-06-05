package UI;

import static org.junit.Assert.assertEquals;

import javafx.stage.Stage;
import org.junit.Test;

class MenuManagerTest {

  @Test
  void getCarCol() {
    Stage primaryStage = new Stage();

    MenuManager ui = new MenuManager(primaryStage, "Red");
    assertEquals(ui.getCarCol(), "Red");
  }

  @Test
  void setCarCol() {
    Stage primaryStage = new Stage();

    MenuManager ui = new MenuManager(primaryStage, "Red");
    ui.setCarCol("Blue");
    assertEquals(ui.getCarCol(), "Blue");
  }
}