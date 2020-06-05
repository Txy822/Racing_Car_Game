package UIManagement;

import UI.MenuManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class UIManager extends Application {


  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setScene(MenuManager.create(primaryStage));
    primaryStage.show();
  }

}
