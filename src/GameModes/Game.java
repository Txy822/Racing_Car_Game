package GameModes;

import Database.DatabaseControl;
import Database.RacingTimer;
import Physics.Camera;
import Physics.Collider.Layers;
import Physics.GameObject;
import Physics.Tracks;
import Physics.Vector2D;
import Server.Client.Client;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public abstract class Game extends Application {

  protected GameObject player;
  protected Tracks gameTrack;
  protected long lastTime = 0;
  protected Client client;
  protected int[][] track;
  protected VBox vb;
  protected DatabaseControl dbControl = new DatabaseControl();
  protected RacingTimer timer = new RacingTimer();
  protected AnchorPane root = new AnchorPane();
  protected Scene scene = new Scene(root, widthVal(), heightVal(), Color.WHITE);
  protected ArrayList<GameObject> trackPieces;
  protected ArrayList<GameObject> powerups = new ArrayList<>();
  protected Camera cam = new Camera();
  protected int lapsCompleted = 0;
  protected boolean startedCrossingFinishline = false;
  // Info panel
  private VBox infoPanel;
  private Text lapCounter;
  private Text currentLapTime;

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

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {

  }

  /**
   * Creates a box on the top right of the screen showing the laps completed and the time lapsed
   */
  protected void createInfoPanel() {
    infoPanel = new VBox();
    infoPanel.setPadding(new Insets(30));

    AnchorPane.setTopAnchor(infoPanel, 0.0);
    AnchorPane.setRightAnchor(infoPanel, 0.0);

    lapCounter = new Text(lapsCompleted + "/" + 3);
    lapCounter.setFont(new javafx.scene.text.Font(50));

    currentLapTime = new Text(millisToMmSs(timer.getTime()));
    currentLapTime.setFont(new Font(50));

    infoPanel.getChildren().addAll(lapCounter, currentLapTime);
    root.getChildren().add(infoPanel);
  }

  private String millisToMmSs(long millis) {
    long minutes = Math.abs((millis / 1000) / 60);
    int seconds = Math.abs(((int) ((millis / 1000) % 60)));

    return (millis < 0 ? "-" : "") + (minutes < 10 ? "0" + minutes : minutes) + ":" + (seconds < 10
        ? "0" + seconds : seconds);
  }

  /**
   * Creates the game object for the player car
   */
  protected void createPlayer() {
    player = new GameObject(new Vector2D(150, 150), new Vector2D(0, 0), new Vector2D(25, 25),
        Layers.CAR);
  }

  protected abstract void createTracks();

  /**
   * Creates a timer to track time elapsed during the game
   */
  protected void createTimer() {
    vb = timer.timeCounter();
  }

  /**
   * Creates the movement physics for the player car to move
   */
  protected void accelerationBraking() {
    scene.setOnKeyPressed(event -> {
      playerMovement(event, player, player.getMaxVelocity());
    });
  }

  /**
   * Adds all the images to be shown on the screen; all track pieces, powerups and the player car
   */
  protected void setRootChildren() {
    root.getChildren().clear();

    for (GameObject trackPiece : trackPieces) {
      root.getChildren().add(trackPiece);
    }

    for (GameObject powerup : powerups) {
      root.getChildren().add(powerup);
    }

    root.getChildren().addAll(player.getImage());
  }

  /**
   * Checks whther the player car has intersected with the finish line, to increase the number of
   * laps completed If the number of laps completed is 3 then the game ends
   *
   * @return whether the car has completed all 3 laps
   */
  protected boolean checkFinish() {

    if (!startedCrossingFinishline) {
      startedCrossingFinishline = player.inFinishLine();
    }

    if (startedCrossingFinishline) {
      if (!player.inFinishLine()) {
        lapsCompleted++;
        lapCounter.setText(lapsCompleted + "/" + 3);

        startedCrossingFinishline = false;

        if (lapsCompleted == 3) {
          return true;
        }

      }
    }

    return false;
  }

  /**
   * Registers the key input from the user and changes the speed in the appropriate direction for
   * the player car Chnages the direction the car is facing depending on the key pressed
   *
   * @param event which keyboard button is pressed
   * @param player the players car object
   * @param maxVelocity the max speed the car can travel in
   */
  protected void playerMovement(KeyEvent event, GameObject player, float maxVelocity) {
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
}
