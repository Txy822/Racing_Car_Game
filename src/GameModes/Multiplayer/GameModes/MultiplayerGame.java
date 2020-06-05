package GameModes.Multiplayer.GameModes;

import GameModes.Multiplayer.MultiplayerSceneManager;
import GameModes.Tracks.Track;
import GameModes.Tracks.TrackImages;
import Physics.Collider;
import Physics.Collider.Layers;
import Physics.CompObject;
import Physics.GameObject;
import Physics.Vector2D;
import Server.Client.Client;
import Server.Game;
import Server.Player;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MultiplayerGame {

  private Track trackOne;
  private GameObject[][] trackObjects;
  private int[][] track;
  private TrackImages trackImages;
  private Game game;
  private int playerId;
  private ArrayList<GameObject> otherPlayers;
  private CompObject compCar;
  private ArrayList<CompObject> allCompCars;
  private ArrayList<GameObject> powerups;


  private GameObject player;

  private Client client;
  private AnchorPane root;
  private VBox infoPanel;

  private long timeA;

  private int lapCount = 0;
  private Text lapCounter;
  private Text currentLapTime;
  private ListView<Text> times;

  private long lastTime = 0;
  private boolean startedCrossingFinishline;

  private Timeline timeline;
  private AnimationTimer mainLoop;

  public MultiplayerGame(Game game, int playerId, Client client,
      MultiplayerSceneManager multiplayerSceneManager) {
    Collider.activeColliders = new ArrayList<>();
    this.game = game;
    this.playerId = playerId;
    this.client = client;

    otherPlayers = initializeOtherPlayers();
    player = initializePlayer();
    initializeTrack();

    // Adds in A.I. cars
    int noAICars = 0;
    noAICars = 3 - otherPlayers.size();
    allCompCars = new ArrayList<CompObject>(noAICars);
    for (int i = 0; i < noAICars; i++) {
      initializeComputer();
    }

    // Creates a new timeline
    timeline = new Timeline(new KeyFrame(Duration.millis(100),
        event -> {
          Game tempState = client.getGameById(game.getId()); // Gets latest game state
          if (tempState.isEnded()) { // If the game has ended
            try {
              timeline.stop();
              mainLoop.stop();
              multiplayerSceneManager.endGame(tempState, playerId);
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        }));

    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();

  }

  /**
   * Detects the width of the screen
   *
   * @return the width of the screen
   */
  public static double widthVal() {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    return screenSize.getWidth();
  }

  /**
   * Detetcts the height of the screen
   *
   * @return the height of the screen
   */
  public static double heightVal() {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    return screenSize.getHeight();
  }

  /**
   * Converts an ArrayList of players to an ArrayList of gameobjects
   */
  private ArrayList<GameObject> playerArrayListToGameObjectExcludeSelf(ArrayList<Player> players) {
    ArrayList<GameObject> gameObjects = new ArrayList<>();

    for (Player player : players) {
      if (player.getId() != playerId) {
        gameObjects.add(new GameObject(new Vector2D(player.getVector().x, player.getVector().y),
            new Vector2D(0, 0), new Vector2D(25, 25),
            Layers.CAR));
      }
    }

    return gameObjects;
  }

  /**
   * Purges the otherPlayers array list
   */
  private void purgeOtherPlayers() {
    otherPlayers.removeAll(otherPlayers);
  }

  /**
   * Called when the game is started
   */
  public void start() {

    // Get starting position
    game = client.getGameById(game.getId());
    ArrayList<Player> temp = new ArrayList<>(game.getPlayers().values());
    temp.removeIf(_player -> _player.getId() != playerId);

    player.setPosition(new Vector2D(temp.get(0).getVector().x, temp.get(0).getVector().y));

    // Used to calculate lap time
    timeA = System.currentTimeMillis();

    String backmusic = MultiplayerGame.class.getResource("/Resources/BackgroundMusic.mp3")
        .toExternalForm();
    Media audio = new Media(backmusic);
    MediaPlayer mediaPlayer = new MediaPlayer(audio);
    mediaPlayer.setCycleCount(5);

    mediaPlayer.play();
    mainLoop = new AnimationTimer() {
      @Override
      public void handle(long now) {
        purgeOtherPlayers();
        game = client.getGameById(game.getId()); // Gets game state

        otherPlayers = playerArrayListToGameObjectExcludeSelf(
            new ArrayList<>(game.getPlayers().values())); // Extracts all other players
        client.updatePlayerPosition(playerId, game.getId(), new Vector2D(player.getPosition().x,
            player.getPosition().y)); // Updates the players position

        // Calculates time delta for physics updates
        long now1 = System.nanoTime();
        float delta = now1 - lastTime;
        lastTime = now1;

        // Updates player and all other players
        player.Update(delta / 1000000000);

        for (GameObject otherPlayers : otherPlayers) {
          otherPlayers.Update(delta / 1000000000);
        }

        // Checks to see if the player has crossed a finish line
        if (!startedCrossingFinishline) {
          startedCrossingFinishline = player.inFinishLine();
        }

        long lapTime = System.currentTimeMillis() - timeA;

        if (startedCrossingFinishline) {
          if (!player.inFinishLine()) {
            lapCount++;
            lapCounter.setText(lapCount + "/" + game.getLaps()); // Updates lap time counter

            if (lapCount == game.getLaps()) {
              mediaPlayer.stop();
              client.endGame(game.getId());
            }

            client.updateLaptime(playerId, game.getId(), lapTime);
            timeA = System.currentTimeMillis(); // Resets timer

            startedCrossingFinishline = false;
          }
        }

        currentLapTime.setText(millisToMmSs(lapTime));

        times.getItems().clear();

        ArrayList<Player> sortedPlayers = game.getSortedTimes();

        // Adds players to the leaderboard with their laptime
        for (Player player : sortedPlayers) {
          if (player.getId() == playerId) {
            times.getItems().add(new Text(player.getPlayerName() + " - " + player.millisToMmSs()));
          } else {
            times.getItems().add(new Text(
                player.getPlayerName() + " - " + player.millisToMmSs() + " (" + millisToMmSs(
                    player.getLastLaptime() - lapTime) + ")"));
          }
        }

        root.getChildren().clear();
        root.getChildren().addAll(createTrack(), createAICars(), createPlayers(), infoPanel);

      }
    };

    mainLoop.start();
  }

  /**
   * Converts a time in millis to MM:SS
   *
   * @return String MM:SS
   */
  private String millisToMmSs(long millis) {
    long minutes = Math.abs((millis / 1000) / 60);
    int seconds = Math.abs(((int) ((millis / 1000) % 60)));

    return (millis < 0 ? "-" : "") + (minutes < 10 ? "0" + minutes : minutes) + ":" + (seconds < 10
        ? "0" + seconds : seconds);
  }

  /**
   * Updates player velocity on key press
   */
  private void playerMovement(KeyEvent event, GameObject player, float maxVelocity) {
    switch (event.getCode()) {
      case UP:
        if (player.getVelocity().y > -maxVelocity) {
          player.getVelocity().y -= 0.25;
          player.setCarRotation("Up");
        }
        break;
      case DOWN:
        if (player.getVelocity().y < maxVelocity) {
          player.getVelocity().y += 0.25;
          player.setCarRotation("Down");
        }
        break;
      case LEFT:
        if (player.getVelocity().x > -maxVelocity) {
          player.getVelocity().x -= 0.25;
          player.setCarRotation("Left");
        }
        break;
      case RIGHT:
        if (player.getVelocity().x < maxVelocity) {
          player.getVelocity().x += 0.25;
          player.setCarRotation("Right");
        }
        break;
    }
  }

  /**
   * Creates the Scene Graph
   *
   * @return Scene graph
   */
  public Scene create() {
    root = new AnchorPane();

    infoPanel = new VBox();
    infoPanel.setPadding(new Insets(30));

    times = new ListView<>();
    AnchorPane.setTopAnchor(infoPanel, 0.0);
    AnchorPane.setRightAnchor(infoPanel, 0.0);
    times.setMouseTransparent(false);
    times.setFocusTraversable(false);

    lapCounter = new Text();
    lapCounter.setText(lapCount + "/" + game.getLaps());
    lapCounter.setFont(new Font(50));

    currentLapTime = new Text();
    currentLapTime.setFont(new Font(50));

    infoPanel.getChildren().addAll(lapCounter, currentLapTime, times);

    root.getChildren().addAll(createTrack(), createPlayers(), infoPanel);

    Scene scene = new Scene(root, widthVal(), heightVal());

    scene.setOnKeyPressed(event -> playerMovement(event, player, player.getMaxVelocity()));

    return scene;
  }

  /**
   * Creates the track game objects
   *
   * @return Parent node of track
   */
  private Node createTrack() {
    Group root = new Group();

    int count = 0;
    for (int i = 0; i < track.length; i++) {
      for (int k = 0; k < track[i].length; k++) {

        // Sets the colliding layer on the track game object
        if (track[i][k] == 1) {
          trackObjects[i][k].setFill(trackImages.getTopLeft());
          trackObjects[i][k].getCollider().layer = Layers.FLOOR;
        } else if (track[i][k] == 2) {
          trackObjects[i][k].setFill(trackImages.getTopRight());
          trackObjects[i][k].getCollider().layer = Layers.FLOOR;
        } else if (track[i][k] == 3) {
          trackObjects[i][k].setFill(trackImages.getBottomRight());
          trackObjects[i][k].getCollider().layer = Layers.FLOOR;
        } else if (track[i][k] == 4) {
          trackObjects[i][k].setFill(trackImages.getBottomLeft());
          trackObjects[i][k].getCollider().layer = Layers.FLOOR;
        } else if (track[i][k] == 5) {
          trackObjects[i][k].setFill(trackImages.getHorizontal());
          trackObjects[i][k].getCollider().layer = Layers.FLOOR;
        } else if (track[i][k] == 6) {
          trackObjects[i][k].setFill(trackImages.getVertical());
          trackObjects[i][k].getCollider().layer = Layers.FLOOR;
        } else if (track[i][k] == 7) {
          trackObjects[i][k].setFill(trackImages.getFinish());
          trackObjects[i][k].getCollider().layer = Layers.FINISH_LINE;
        } else if (track[i][k] == 0) {
          trackObjects[i][k].setFill(trackImages.getBackground());
          trackObjects[i][k].getCollider().layer = Layers.WALL;
        }

        trackOne.getRectangles()[count] = trackObjects[i][k];
        count++;
        root.getChildren().add(trackObjects[i][k]);
      }
    }

    for (GameObject powerup : powerups) {
      root.getChildren().add(powerup);
    }

    return root;
  }

  /**
   * Creates the players parent Node
   *
   * @return allof the player images
   */
  private Node createPlayers() {
    Group root = new Group();

    for (GameObject player : otherPlayers) {
      root.getChildren().add(player.getImage());
    }

    root.getChildren().add(player.getImage());

    return root;
  }

  /**
   * Creates the AI players parent Node
   *
   * @return all the computer car images
   */
  private Node createAICars() {
    Group root = new Group();

    for (CompObject car : allCompCars) {
      root.getChildren().add(car.getRectangle());
    }

    return root;
  }

  /**
   * Creates an instance of a computer controlled car and adds it to the list of all computer
   * controlled cars
   */
  private void initializeComputer() {
    compCar = new CompObject(trackOne.getAIroute(), trackOne.getTrackRectangles());
    allCompCars.add(compCar);
  }

  /**
   * Intializes the other players as game objects
   *
   * @return the list of all player game objects
   */
  private ArrayList<GameObject> initializeOtherPlayers() {
    ArrayList<GameObject> otherPlayers = new ArrayList<>();

    for (Player player : game.getPlayers().values()) { // Iterates over the Player ArrayList
      if (player.getId() != playerId) {
        otherPlayers.add(new GameObject(new Vector2D(player.getVector().x, player.getVector().y),
            new Vector2D(0, 0),
            new Vector2D(25, 25), Layers.CAR));
      }
    }

    return otherPlayers;
  }

  /**
   * Intiliazes the Player as a gameobject
   *
   * @return return player game object
   */
  private GameObject initializePlayer() {
    Player player = game.getPlayers().get(playerId);

    GameObject car = new GameObject(new Vector2D(player.getVector().x, player.getVector().y),
        new Vector2D(0, 0),
        new Vector2D(25, 25), Layers.CAR);

    String imageName = MultiplayerGame.class.getResource("/Resources/Cars/CarBlue.png")
        .toExternalForm();
    Image image = new Image(imageName);
    ImagePattern carImage = new ImagePattern(image);
    car.setFill(carImage);

    return car;
  }

  /**
   * Initializes each track piece as a game object and stores them in a list Initializes each
   * powerup as a game object and store those in a separate list
   */
  private void initializeTrack() {
    trackOne = game.getTrack();
    track = trackOne.getLayout();
    trackImages = new TrackImages();
    int noTrackRectangles = 0;

    trackObjects = new GameObject[track.length][track[0].length];

    GameObject node;

    int count = 0;
    for (int i = 0; i < track.length; i++) {
      for (int k = 0; k < track[i].length; k++) {

        // Creates the game object for the track segment with a collider
        if (track[i][k] == 1) {
          node = new GameObject(new Vector2D(k * 100, i * 100), new Vector2D(0, 0),
              new Vector2D(100, 100),
              Layers.FLOOR);
          node.setFill(trackImages.getTopLeft());
          noTrackRectangles++;
        } else if (track[i][k] == 2) {
          node = new GameObject(new Vector2D(k * 100, i * 100), new Vector2D(0, 0),
              new Vector2D(100, 100),
              Layers.FLOOR);
          node.setFill(trackImages.getTopRight());
          noTrackRectangles++;
        } else if (track[i][k] == 3) {
          node = new GameObject(new Vector2D(k * 100, i * 100), new Vector2D(0, 0),
              new Vector2D(100, 100),
              Layers.FLOOR);
          node.setFill(trackImages.getBottomRight());
          noTrackRectangles++;
        } else if (track[i][k] == 4) {
          node = new GameObject(new Vector2D(k * 100, i * 100), new Vector2D(0, 0),
              new Vector2D(100, 100),
              Layers.FLOOR);
          node.setFill(trackImages.getBottomLeft());
          noTrackRectangles++;
        } else if (track[i][k] == 5) {
          node = new GameObject(new Vector2D(k * 100, i * 100), new Vector2D(0, 0),
              new Vector2D(100, 100),
              Layers.FLOOR);
          node.setFill(trackImages.getHorizontal());
          noTrackRectangles++;
        } else if (track[i][k] == 6) {
          node = new GameObject(new Vector2D(k * 100, i * 100), new Vector2D(0, 0),
              new Vector2D(100, 100),
              Layers.FLOOR);
          node.setFill(trackImages.getVertical());
          noTrackRectangles++;
        } else if (track[i][k] == 7) {
          node = new GameObject(new Vector2D(k * 100, i * 100), new Vector2D(0, 0),
              new Vector2D(100, 100),
              Layers.FINISH_LINE);
          node.setFill(trackImages.getFinish());
          noTrackRectangles++;
        } else {
          node = new GameObject(new Vector2D(k * 100, i * 100), new Vector2D(0, 0),
              new Vector2D(100, 100),
              Layers.WALL);
          node.setFill(trackImages.getBackground());
        }
        trackObjects[i][k] = node;
        trackOne.getRectangles()[count] = node;
        count++;
      }
    }

    Rectangle[] trackRectangles = new Rectangle[noTrackRectangles];
    count = 0;
    for (int i = 0; i < track.length; i++) {
      for (int k = 0; k < track[i].length; k++) {
        if (track[i][k] > 0) {
          trackRectangles[count] = new Rectangle((k * 100) + 50, (i * 100) + 50, 100, 100);
          count++;
        }
      }
    }

    trackOne.setTrackRectangles(trackRectangles);

    powerups = new ArrayList<>();
    GameObject powerup = new GameObject(new Vector2D(400, 110), new Vector2D(0, 0),
        new Vector2D(30, 30), Layers.OIL);
    powerup.setFill(trackImages.getOil());
    powerups.add(powerup);
    powerup = new GameObject(new Vector2D(500, 610), new Vector2D(0, 0), new Vector2D(30, 30),
        Layers.OIL);
    powerup.setFill(trackImages.getOil());
    powerups.add(powerup);
    powerup = new GameObject(new Vector2D(530, 110), new Vector2D(0, 0), new Vector2D(30, 30),
        Layers.STAR);
    powerup.setFill(trackImages.getStar());
    powerups.add(powerup);
    powerup = new GameObject(new Vector2D(330, 610), new Vector2D(0, 0), new Vector2D(30, 30),
        Layers.STAR);
    powerup.setFill(trackImages.getStar());
    powerups.add(powerup);
  }
}
