package GameModes.Multiplayer.UI.GameLobby;

import GameModes.Multiplayer.MultiplayerSceneManager;
import GameModes.Multiplayer.UI.Toast.Toast;
import GameModes.Tracks.Track;
import GameModes.Tracks.TrackImages;
import Server.Client.Client;
import Server.Game;
import Server.Player;
import UI.MenuManager;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class GameLobby {

  private Game game;
  private Client client;
  private int playerId;
  private MultiplayerSceneManager multiplayerSceneManager;
  private MenuManager ui;

  private Timeline timeline;

  @FXML
  private AnchorPane root;

  @FXML
  private HBox toolbar;

  @FXML
  private VBox playersBox;

  @FXML
  private HBox bottomBox;

  @FXML
  private HBox bottom;

  @FXML
  private ListView<String> playersListView;

  @FXML
  private HBox wrapperBox;

  @FXML
  private Group trackPreview;

  @FXML
  private Text gameNameText;

  @FXML
  private StackPane contentParent;

  @FXML
  private VBox content;

  @FXML
  private Text trackTitle;

  @FXML
  private Text trackLaps;

  public GameLobby(Game game, Client client, int playerId,
      MultiplayerSceneManager multiplayerSceneManager, MenuManager ui) {
    this.game = game;
    this.client = client;
    this.playerId = playerId;
    this.multiplayerSceneManager = multiplayerSceneManager;
    this.ui = ui;

    // Timeline which ensures lobby is responsive
    timeline = new Timeline(new KeyFrame(Duration.millis(100),
        event -> {
          if (client.checkGameStarted(game.getId())) {
            startGame();
          }

          createPlayerList();
        }));

    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();

  }

  public static int widthVal() {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    return (int) screenSize.getWidth();
  }

  public static int heightVal() {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    return (int) screenSize.getHeight();
  }

  /**
   * Starts the game
   */
  public void startGame() {
    if (timeline != null) {
      timeline.stop();
    }
    multiplayerSceneManager.startGame(game, playerId);
  }

  /**
   * Creates the player list
   */
  public void createPlayerList() {
    game = client.getGameById(game.getId());
    playersListView.getItems().clear();
    playersListView.setEditable(false);
    playersListView.setStyle("-fx-border-color: black;");

    for (Player player : game.getPlayers().values()) {
      playersListView.getItems().add(player.getPlayerName());
    }
  }

  /**
   * Creates the leave button
   */
  public void leaveButton() {
    Button leaveButton = new Button();
    leaveButton.setOnAction(event -> {
      timeline.stop();
      try {
        multiplayerSceneManager.leaveGame(game, playerId);
      } catch (Exception e) {
        e.printStackTrace();
      }
    });

    Image image = new Image(getClass().getResourceAsStream("/Resources/Button/backButton.png"));
    ImageView imageView = new ImageView(image);
    imageView.setFitHeight(image.getHeight() * 0.8);
    imageView.setFitWidth(image.getWidth() * 0.8);
    imageView.setPreserveRatio(true);

    leaveButton.setStyle("-fx-background-color: none;\n"
        + "  -fx-border-color: none;");

    leaveButton.setGraphic(imageView);
    bottomBox.getChildren().add(leaveButton);
  }

  /**
   * Creates the track preview
   */
  public void createTrackPreview() {
    TrackImages trackImages = new TrackImages();
    trackTitle.setText(game.getTrack().toString());
    trackLaps.setText(game.getLaps() + " laps");

    Track track = game.getTrack();
    int[][] trackLayout = track.getLayout();

    int count = 0;
    // Iterates over the track layout generating Nodes for each element
    for (int i = 0; i < trackLayout.length; i++) {
      for (int k = 0; k < trackLayout[i].length; k++) {
        Rectangle node = new Rectangle(50, 50);

        // Sets image of the node
        if (trackLayout[i][k] == 1) {
          node.setFill(trackImages.getTopLeft());
        } else if (trackLayout[i][k] == 2) {
          node.setFill(trackImages.getTopRight());
        } else if (trackLayout[i][k] == 3) {
          node.setFill(trackImages.getBottomRight());
        } else if (trackLayout[i][k] == 4) {
          node.setFill(trackImages.getBottomLeft());
        } else if (trackLayout[i][k] == 5) {
          node.setFill(trackImages.getHorizontal());
        } else if (trackLayout[i][k] == 6) {
          node.setFill(trackImages.getVertical());
        } else if (trackLayout[i][k] == 7) {
          node.setFill(trackImages.getFinish());
        } else {
          node.setFill(trackImages.getBackground());
        }

        // Calculates the scale so that it fits perfectly
        int xScale = (int) Math.round((multiplayerSceneManager.WIDTH / 4.0f) / track.getWidth());
        int yScale = (int) Math.round((multiplayerSceneManager.WIDTH / 4.0f) / track.getHeight());

        node.setX(k * xScale);
        node.setY(i * yScale);
        node.setWidth(xScale);
        node.setHeight(yScale);

        track.getRectangles()[count] = node;
        count++;
        trackPreview.getChildren().add(node);
      }
    }
  }

  /**
   * Creates the start button
   */
  public void createStartButton() {
    Button startGame = new Button();
    startGame.setOnAction(event -> {
      if (game.getPlayers().size() < 2) {

        // Displays a popup message

        String toastMsg = "Cannot start game without at least 2 players";
        int toastMsgTime = 3500; //3.5 seconds
        int fadeInTime = 500; //0.5 seconds
        int fadeOutTime = 500; //0.5 seconds
        Toast
            .makeText(multiplayerSceneManager.getPrimaryStage(), toastMsg, toastMsgTime, fadeInTime,
                fadeOutTime);
        return;
      }

      client.startGame(game.getId());
    });

    Image image = new Image(getClass().getResourceAsStream("/Resources/Button/playButton.png"));
    ImageView imageView = new ImageView(image);
    imageView.setFitHeight(image.getHeight() * 0.8);
    imageView.setFitWidth(image.getWidth() * 0.8);
    imageView.setPreserveRatio(true);

    startGame.setStyle("-fx-background-color: none;\n"
        + "  -fx-border-color: none;");

    startGame.setGraphic(imageView);

    bottomBox.getChildren().add(startGame);
  }

  /**
   * Creates the Scene graph
   *
   * @return Scene graph
   */
  public Scene create() {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(GameLobby.class.getResource("GameLobby.fxml"));
    loader.setController(this);
    try {
      root = loader.load();
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }

    root.getStylesheets().add(GameLobby.class.getResource("GameLobby.css").toExternalForm());

    createPlayerList();
    leaveButton();
    createTrackPreview();

    if (playerId == 0) { // If the player is the host show the start button
      createStartButton();
    }

    wrapperBox.setPadding(new Insets(10));
    wrapperBox.setSpacing(100);
    wrapperBox.setAlignment(Pos.CENTER);

    gameNameText.setText(game.getGameName());

    String background = GameLobby.class.getResource("/Resources/BG/RoomBG.png").toExternalForm();
    root.setStyle("-fx-background-image: url('" + background + "'); " +
        "-fx-background-position: center center; " +
        "-fx-background-repeat: stretch;"
        + "-fx-background-size: " + multiplayerSceneManager.WIDTH + ";");

    contentParent.setAlignment(content, Pos.CENTER);
    contentParent.setTranslateX(-250);

    return new Scene(root, widthVal(), heightVal());
  }
}
