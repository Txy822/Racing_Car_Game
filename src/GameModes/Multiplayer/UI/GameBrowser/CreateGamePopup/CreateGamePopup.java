package GameModes.Multiplayer.UI.GameBrowser.CreateGamePopup;

import GameModes.Multiplayer.UI.GameBrowser.GameBrowser;
import GameModes.Multiplayer.UI.Popup.Popup;
import GameModes.Tracks.Track;
import GameModes.Tracks.TrackManager;
import Server.Client.Client;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CreateGamePopup {

  private AnchorPane popupRoot;
  private GameBrowser gameBrowser;
  private Client client;

  @FXML
  private AnchorPane root;

  @FXML
  private HBox toolbar;

  @FXML
  private Button closeButton;

  @FXML
  private TextField createGameName;

  @FXML
  private Button createGameButton;

  @FXML
  private Slider maxPlayersSlider;

  @FXML
  private Spinner<Integer> laps;

  @FXML
  private VBox inputs;

  @FXML
  private VBox wrapper;

  @FXML
  private ComboBox<Track> trackSelector;

  public CreateGamePopup(GameBrowser gameBrowser, Client client) {
    Popup popup = new Popup();
    popupRoot = popup.create();

    this.gameBrowser = gameBrowser;
    this.client = client;
  }

  public void createCreateGameButton() {
    createGameButton.setOnAction(event -> {
      if (createGameName.getText().isEmpty() || trackSelector.getValue()
          == null) { //If the name of the game is empty or a track isn't selected then return
        return;
      }

      int lapsValue = laps.getValueFactory().valueProperty().get(); // Get the number of laps

      int gameId = client.createGame(createGameName.getText(), (int) maxPlayersSlider.getValue(),
          trackSelector.getValue(),
          lapsValue);
      try {
        gameBrowser.joinGame(gameId);
      } catch (Exception e) {
        e.printStackTrace();
      }
    });

    createGameName.setText(gameBrowser.getPlayerName() + "'s game");
  }

  public void createMaxPlayersSlider() {
    maxPlayersSlider.setMin(1);
    maxPlayersSlider.setMax(4);
    maxPlayersSlider.setMajorTickUnit(1);
    maxPlayersSlider.setMinorTickCount(0);
    maxPlayersSlider.setShowTickMarks(true);
    maxPlayersSlider.setShowTickLabels(true);
    maxPlayersSlider.setSnapToTicks(true);
  }

  /**
   * Creates the Scene graph for the popup
   */
  public Node create() {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(CreateGamePopup.class.getResource("CreateGamePopup.fxml"));
    loader.setController(this);

    try {
      root = loader.load();
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }

    root.getStylesheets()
        .add(CreateGamePopup.class.getResource("CreateGamePopup.css").toExternalForm());

    closeButton.setOnAction(event -> {
      gameBrowser.toggleCreateGamePopup();
    });

    popupRoot.getChildren().add(root);

    createMaxPlayersSlider();
    createCreateGameButton();

    inputs.setPadding(new Insets(10));

    // Value factory for the laps spinner
    SpinnerValueFactory<Integer> valueFactory =
        new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 3);

    laps.setValueFactory(valueFactory);

    TrackManager trackManager = new TrackManager();

    trackSelector.getItems().add(trackManager.getTrackOne());

    return popupRoot;
  }


}
