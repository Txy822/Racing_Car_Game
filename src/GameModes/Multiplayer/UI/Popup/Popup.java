package GameModes.Multiplayer.UI.Popup;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class Popup {

  @FXML
  private AnchorPane root;

  /**
   * Returns the parent Node for the popup
   */
  public AnchorPane create() {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(Popup.class.getResource("Popup.fxml"));
    loader.setController(this);

    try {
      root = loader.load();
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }

    root.getStylesheets().add(Popup.class.getResource("Popup.css").toExternalForm());

    return root;

  }

}
