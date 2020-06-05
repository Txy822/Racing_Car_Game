package GameModes.Multiplayer.UI.GameBrowser;

import GameModes.Multiplayer.MultiplayerSceneManager;
import GameModes.Multiplayer.UI.GameBrowser.CreateGamePopup.CreateGamePopup;
import GameModes.Multiplayer.UI.GameBrowser.GameItem.GameItem;
import GameModes.Multiplayer.UI.Toast.Toast;
import Server.Client.Client;
import Server.Game;
import UI.MenuManager;
import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class GameBrowser {

  private Client client;
  private MultiplayerSceneManager multiplayerSceneManager;
  private MenuManager ui;
  private Timeline timeline;

  private boolean createGamePopupOpen = false;
  private CreateGamePopup createGamePopup;

  @FXML
  private AnchorPane root;

  @FXML
  private TextField playerNameInput;

  @FXML
  private Button playerNameButton;

  @FXML
  private HBox backBox;

  @FXML
  private Button backButton;

  @FXML
  private VBox gamesBox;

  @FXML
  private Button createGameButton;

  @FXML
  private StackPane popupParent;

  @FXML
  private HBox toolbar;

  @FXML
  private StackPane contentParent;

  @FXML
  private VBox content;

  public GameBrowser(Client client, MultiplayerSceneManager multiplayerSceneManager,
      MenuManager ui) {
    this.client = client;
    this.multiplayerSceneManager = multiplayerSceneManager;
    this.ui = ui;
  }

  /**
   * Instantiates any time lines used
   */
  private void registerTimelines() {
    timeline = new Timeline(new KeyFrame(Duration.millis(500),
        event -> {
          createGameButtons();
        }));

    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
  }

  /**
   * Creates the return button
   */
  private void createReturnToGameSelectionButton() throws Exception {
    String backSrc = "src/resources/Button/backButton.png";

    backButton = ui.newButton(backSrc, 0.8);
    backButton.setOnAction(e -> {
      try {
        multiplayerSceneManager.returnToGameSelection();
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    });
    backBox.getChildren().add(backButton);
  }

  /**
   * Creates the create game button
   */
  private void createGameButtons() {
    ArrayList<Game> games = client.getGames();
    gamesBox.getChildren().clear();

    for (Game game : games) {
      GameItem gameItem = new GameItem(game);
      Node gameNode = gameItem.create();
      gameItem.getJoinButton().setOnAction(event -> {
        if (game.getPlayers().values().size() < game.getMaxPlayers() && !game.isStarted()) {
          multiplayerSceneManager.joinGame(game);
        }
      });

      gamesBox.getChildren().add(gameNode);
    }

    gamesBox.setSpacing(5);
  }

  /**
   * Creates the player name input
   */
  private void createPlayerNameInput() {
    playerNameButton.setOnAction(event -> {
      multiplayerSceneManager.setPlayerName(playerNameInput.getText());
      String toastMsg = "Name changed";
      int toastMsgTime = 3500; //3.5 seconds
      int fadeInTime = 500; //0.5 seconds
      int fadeOutTime = 500; //0.5 seconds
      Toast.makeText(multiplayerSceneManager.getPrimaryStage(), toastMsg, toastMsgTime, fadeInTime,
          fadeOutTime);
    });
  }

  private void createCreateGameButton() {
    createGameButton.setOnAction(event -> {
      toggleCreateGamePopup();
    });
  }

  private void createCreateGamePopup() {
    createGamePopup = new CreateGamePopup(this, client);
    Node createGamePopupNode = createGamePopup.create();

    popupParent.getChildren().add(createGamePopupNode);
    popupParent.setAlignment(createGamePopupNode, Pos.CENTER);

    root.getChildren().remove(popupParent);
  }

  public void toggleCreateGamePopup() {
    createGamePopup.createCreateGameButton();

    if (!createGamePopupOpen) {
      root.getChildren().add(popupParent);
    } else {
      root.getChildren().remove(popupParent);
    }

    createGamePopupOpen = !createGamePopupOpen;
  }

  /**
   * Joins a game
   */
  public void joinGame(int gameId) throws Exception {
    Game game = client.getGameById(gameId);

    if (game.isStarted()) {
      // Displays popup
      String toastMsg = "Game started!";
      int toastMsgTime = 3500; //3.5 seconds
      int fadeInTime = 500; //0.5 seconds
      int fadeOutTime = 500; //0.5 seconds
      Toast.makeText(multiplayerSceneManager.getPrimaryStage(), toastMsg, toastMsgTime, fadeInTime,
          fadeOutTime);
      return;
    }

    if (game.getPlayers().size() == game.getMaxPlayers()) {
      // Displays popup
      String toastMsg = "Game full!";
      int toastMsgTime = 3500; //3.5 seconds
      int fadeInTime = 500; //0.5 seconds
      int fadeOutTime = 500; //0.5 seconds
      Toast.makeText(multiplayerSceneManager.getPrimaryStage(), toastMsg, toastMsgTime, fadeInTime,
          fadeOutTime);
      return;
    }

    multiplayerSceneManager.joinGame(client.getGameById(gameId));
  }

  /**
   * Creates the Scene graph
   *
   * @return Scene graph
   */
  public Scene create() throws Exception {

    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(GameBrowser.class.getResource("GameBrowser.fxml"));
    loader.setController(this);
    try {
      root = loader.load();
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }

    root.getStylesheets().add(GameBrowser.class.getResource("GameBrowser.css").toExternalForm());

    createPlayerNameInput();
    createGameButtons();
    createCreateGameButton();
    createReturnToGameSelectionButton();
    createCreateGamePopup();

    registerTimelines();

    String background = GameBrowser.class.getResource("/Resources/BG/MultiplayerBG.png")
        .toExternalForm();
    root.setStyle("-fx-background-image: url('" + background + "'); " +
        "-fx-background-position: center center; " +
        "-fx-background-repeat: stretch;"
        + "-fx-background-size: " + multiplayerSceneManager.WIDTH + ";");

    contentParent.setAlignment(content, Pos.CENTER);
    contentParent.setTranslateX(-250);

    return new Scene(root, multiplayerSceneManager.WIDTH, multiplayerSceneManager.HEIGHT);
  }

  public String getPlayerName() {
    return multiplayerSceneManager.getPlayerName();
  }

}
