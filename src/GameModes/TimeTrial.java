package GameModes;

import GameModes.Multiplayer.GameModes.MultiplayerGame;
import GameModes.Tracks.TrackImages;
import Physics.Collider;
import Physics.Collider.Layers;
import Physics.GameObject;
import Physics.Tracks;
import Physics.Vector2D;
import UI.EndGameSingleplayer;
import UI.MenuManager;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class TimeTrial extends Game {

  private static int TrackID;
  private static int playerID;
  private static MenuManager ui;
  AnimationTimer mainLoop;
  private int squareSize = 150;


  public TimeTrial(MenuManager ui) {
    this.ui = ui;
  }

  public static int getTrackID() {
    return TrackID;
  }

  public static void setTrackID(int trackID) {
    TrackID = trackID;
  }

  public static int getPlayerID() {
    return playerID;
  }

  public static void setPlayerID(int playerID) {
    TimeTrial.playerID = playerID;
  }

  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Called when the game is started
   */
  @Override
  public void start(Stage primaryStage) {
    int gameId = 1;
    setPlayerID(ui.getID());// player ID
    setTrackID(2);// assign time trial as trackId 2

    Collider.activeColliders = new ArrayList<>();

    try {
      createPlayer();

      createTracks();

      createTimer();

      String backmusic = MultiplayerGame.class.getResource("/Resources/BackgroundMusic.mp3")
          .toExternalForm();
      Media audio = new Media(backmusic);
      MediaPlayer mediaPlayer = new MediaPlayer(audio);
      mediaPlayer.setCycleCount(5);

      accelerationBraking();

      // The window is displayed to the screen
      primaryStage.setScene(scene);
      primaryStage.setTitle("Track1");
      primaryStage.show();
      mediaPlayer.play();

      // Updates the scene
      mainLoop = new AnimationTimer() {
        @Override
        public void handle(long now) {
          // Updates the players player position
          long now1 = System.nanoTime();
          float delta = now1 - lastTime;
          lastTime = now1;

          //updatePlayer();
          player.Update(delta / 1000000000);

          if (checkFinish() == true && lapsCompleted == 3) {
            mediaPlayer.stop();
            mainLoop.stop();
            timer.stop();
            dbControl.updateLeaderBoard(getPlayerID(), timer.getTime(), getTrackID());
            dbControl.updatePlayersTime(getPlayerID(), timer.getTime(), getTrackID());
            try {
              EndGameSingleplayer end = new EndGameSingleplayer(ui,
                  timer.msToTime(timer.getTime()));
              primaryStage.setScene(end.create(primaryStage));
            } catch (Exception e) {
              e.printStackTrace();
            }
            primaryStage.setTitle("Game Finished");
            primaryStage.show();
          }

          // Clears all the items on the screen to redraw them at the new position
          setRootChildren();
          primaryStage.show();

        }


      };

      mainLoop.start();

    } catch (Exception e) {
      e.printStackTrace();
    }


  }

  /**
   * Creates the game object for the player car
   */
  @Override
  protected void createPlayer() {
    player = new GameObject(new Vector2D(450, 450), new Vector2D(0, 0), new Vector2D(50, 50),
        Layers.CAR);
  }

  /**
   * Adds all the images to be shown on the screen; all track pieces, powerups and the player car
   * For this specific game it adds the camera offset to the objects to position the cra in the
   * centre of the screen
   */
  @Override
  protected void setRootChildren() {
    root.getChildren().clear();
    for (GameObject trackPiece : trackPieces) {
      trackPiece.setX(trackPiece.getX() + cam.getXoffset());
      trackPiece.setY(trackPiece.getY() + cam.getYoffset());
    }

    for (GameObject powerup : powerups) {
      powerup.setX(powerup.getX() + cam.getXoffset());
      powerup.setY(powerup.getY() + cam.getYoffset());
    }

    cam.setXoffset(player.getPosition().x - 300);
    cam.setYoffset(player.getPosition().y - 300);

    for (GameObject trackPiece : trackPieces) {
      trackPiece.setX(trackPiece.getX() - cam.getXoffset());
      trackPiece.setY(trackPiece.getY() - cam.getYoffset());
      root.getChildren().add(trackPiece);
    }

    for (GameObject powerup : powerups) {
      powerup.setX(powerup.getX() - cam.getXoffset());
      powerup.setY(powerup.getY() - cam.getYoffset());
      root.getChildren().add(powerup);
    }

    player.getImage().setX(player.getPosition().x - cam.getXoffset());
    player.getImage().setY(player.getPosition().y - cam.getYoffset());

    root.getChildren().addAll(player.getImage());
    createInfoPanel();
  }

  /**
   * Creates each track piece as a game object and saves them to a list
   */
  @Override
  protected void createTracks() {
    gameTrack = new Tracks();
    gameTrack.track1();
    track = gameTrack.getTrack1();
    TrackImages trackImages = new TrackImages();
    trackPieces = new ArrayList<>();
    GameObject node;

    for (int i = 0; i < track.length; i++) {
      for (int k = 0; k < track[i].length; k++) {

        if (track[i][k] == 1) {
          node = new GameObject(new Vector2D(k * squareSize, i * squareSize), new Vector2D(0, 0),
              new Vector2D(squareSize, squareSize),
              Layers.FLOOR);
          node.setFill(trackImages.getTopLeft());
        } else if (track[i][k] == 2) {
          node = new GameObject(new Vector2D(k * squareSize, i * squareSize), new Vector2D(0, 0),
              new Vector2D(squareSize, squareSize),
              Layers.FLOOR);
          node.setFill(trackImages.getTopRight());
        } else if (track[i][k] == 3) {
          node = new GameObject(new Vector2D(k * squareSize, i * squareSize), new Vector2D(0, 0),
              new Vector2D(squareSize, squareSize),
              Layers.FLOOR);
          node.setFill(trackImages.getBottomRight());
        } else if (track[i][k] == 4) {
          node = new GameObject(new Vector2D(k * squareSize, i * squareSize), new Vector2D(0, 0),
              new Vector2D(squareSize, squareSize),
              Layers.FLOOR);
          node.setFill(trackImages.getBottomLeft());
        } else if (track[i][k] == 5) {
          node = new GameObject(new Vector2D(k * squareSize, i * squareSize), new Vector2D(0, 0),
              new Vector2D(squareSize, squareSize),
              Layers.FLOOR);
          node.setFill(trackImages.getHorizontal());
        } else if (track[i][k] == 6) {
          node = new GameObject(new Vector2D(k * squareSize, i * squareSize), new Vector2D(0, 0),
              new Vector2D(squareSize, squareSize),
              Layers.FLOOR);
          node.setFill(trackImages.getVertical());
        } else if (track[i][k] == 7) {
          node = new GameObject(new Vector2D(k * squareSize, i * squareSize), new Vector2D(0, 0),
              new Vector2D(squareSize, squareSize),
              Layers.FINISH_LINE);
          node.setFill(trackImages.getFinish());
        } else {
          node = new GameObject(new Vector2D(k * squareSize, i * squareSize), new Vector2D(0, 0),
              new Vector2D(squareSize, squareSize),
              Layers.WALL);
          node.setFill(trackImages.getBackground());
        }

        trackPieces.add(node);
      }
    }
  }

}
