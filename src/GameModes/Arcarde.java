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
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Arcarde extends Game {

  private static int TrackID;
  private static int playerID;
  AnimationTimer mainLoop;
  MenuManager ui;
  private ArrayList<GameObject> trackRoute;

  public Arcarde(MenuManager ui) {
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
    Arcarde.playerID = playerID;
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
    setTrackID(1);// assigning archarde  as trackID 1

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
          createInfoPanel();
          primaryStage.show();

        }


      };

      mainLoop.start();

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   * Creates each track piece as a game object and saves them to a list Creates 4 powerups in a
   * random position on the track and add them to a separate list
   */
  @Override
  protected void createTracks() {
    gameTrack = new Tracks();
    TrackImages trackImages = new TrackImages();
    gameTrack.randomtrack();
    track = gameTrack.getMap();
    trackPieces = new ArrayList<>();
    trackRoute = new ArrayList<>();

    for (int i = 0; i < track.length; i++) {
      for (int k = 0; k < track[i].length; k++) {
        GameObject node = new GameObject(new Vector2D(k * 100, i * 100), new Vector2D(0, 0),
            new Vector2D(100, 100),
            Layers.FLOOR);

        if (track[i][k] == 3) {
          node.setFill(trackImages.getBackRoute());
          trackRoute.add(node);
        } else if (track[i][k] == 4) {
          node.setFill(trackImages.getRandomFinish());
          node.getCollider().layer = Layers.FINISH_LINE;
        } else {
          node.setFill(trackImages.getBackground());
          node.getCollider().layer = Layers.WALL;
        }

        trackPieces.add(node);
      }
    }

    Random rand = new Random();
    int minimum = 0;
    GameObject powerup;
    for (int m = 0; m < 4; m++) {
      int maximum = trackRoute.size() - 1;
      int square = rand.nextInt((maximum - minimum) + 1) + minimum;
      GameObject temp = trackRoute.remove(square);

      if (m < 2) {
        powerup = new GameObject(new Vector2D((float) temp.getX(), (float) (temp.getY() + 20)),
            new Vector2D(0, 0), new Vector2D(30, 30), Layers.OIL);
        powerup.setFill(trackImages.getOil());
      } else {
        powerup = new GameObject(new Vector2D((float) temp.getX(), (float) (temp.getY() + 20)),
            new Vector2D(0, 0), new Vector2D(30, 30), Layers.STAR);
        powerup.setFill(trackImages.getStar());
      }

      powerups.add(powerup);
    }
  }
}
