package Server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import GameModes.Tracks.Track;
import GameModes.Tracks.TrackManager;
import Physics.Vector2D;
import Server.Exceptions.GameStartedException;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

public class GameTest {

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void setStartPositions() throws GameStartedException {
    Game game = new Game(new HashMap<>(), null, 0, null, 0);
    game.addPlayer(new Player("Test", 0, new Vector2D(0, 0), "Red"));
    game.setStartPositions();

    assertNotNull(game.getPlayers().get(0).getVector());
  }

  @Test
  public void getPlayers() {
  }

  @Test
  public void addPlayer() {
  }

  @Test(expected = GameStartedException.class)
  public void addPlayerGameStarted() throws GameStartedException {
    Game game = new Game(new HashMap<>(), null, 0, null, 0);
    game.startGame();
    game.addPlayer(new Player("Test", 0, new Vector2D(0, 0), "Red"));

  }

  @Test
  public void removePlayer() {
  }

  @Test
  public void hasPlayer() {
  }

  @Test
  public void startGame() {
  }

  @Test(expected = GameStartedException.class)
  public void startGameException() throws GameStartedException {
    Game game = new Game(new HashMap<>(), null, 0, null, 0);
    game.startGame();
    game.startGame();
  }


  @Test
  public void getGameName() {
  }

  @Test
  public void setGameName() {
    Game game = new Game(new HashMap<>(), null, 0, null, 0);
    game.setGameName("Test");

    assertEquals("Test", game.getGameName());
  }

  @Test
  public void getId() {
  }

  @Test
  public void setId() {
  }

  @Test
  public void isStarted() {
  }

  @Test
  public void getStartPosition() {
  }

  @Test
  public void setStartPosition() {
    Game game = new Game(new HashMap<>(), null, 0, null, 0);
    Vector2D vector2D = new Vector2D(0, 0);
    game.setStartPosition(vector2D);

    assertEquals(vector2D, game.getStartPosition());
  }

  @Test
  public void getMaxPlayers() {
  }

  @Test
  public void setMaxPlayers() {
    Game game = new Game(new HashMap<>(), null, 0, null, 0);
    game.setMaxPlayers(5);

    assertEquals(5, game.getMaxPlayers());
  }

  @Test
  public void getTrack() {
  }

  @Test
  public void setTrack() {
    Game game = new Game(new HashMap<>(), null, 0, null, 0);

    TrackManager trackManager = new TrackManager();
    Track track = trackManager.getTrackOne();
    game.setTrack(track);

    assertEquals(track, game.getTrack());
  }

  @Test
  public void getPlayerCounter() {
  }

  @Test
  public void getLaps() {
  }

  @Test
  public void setLaps() {
    Game game = new Game(new HashMap<>(), null, 0, null, 0);
    game.setLaps(5);

    assertEquals(5, game.getLaps());
  }

  @Test
  public void isEnded() {
  }

  @Test
  public void setEnded() {
  }
}