package Server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import Physics.Vector2D;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

public class MultiplayerGameManagerTest {

  private MultiplayerGameManager multiplayerGameManager;
  private String carColour = "Red";


  @Test
  public void addGame() {
    int originalSize = multiplayerGameManager.getGames().size();
    int id = multiplayerGameManager.addGame(new Game(null, null, 5, null, 2));
    assertEquals(multiplayerGameManager.getGames().size(), originalSize + 1);
    multiplayerGameManager.getGames().remove(id);
  }

  @Test
  public void connectToGame() {
    int gameId = multiplayerGameManager.addGame(new Game(new HashMap<>(), "Test name", 5, null,
        3));
    int playerId = multiplayerGameManager.connectToGame(gameId, new Vector2D(0, 3), "Player");
    assertNotEquals(multiplayerGameManager.getGames().get(gameId).getPlayers().get(playerId), null);
    multiplayerGameManager.getGames().remove(gameId);
  }

  @Test
  public void disconnectFromGame() {
    int gameId = multiplayerGameManager.addGame(new Game(new HashMap<>(), "Test name", 5, null,
        3));
    int playerId = multiplayerGameManager.connectToGame(gameId, null, "Player");
    multiplayerGameManager.disconnectFromGame(gameId, playerId);
    assertEquals(multiplayerGameManager.getGames().get(gameId).getPlayers().containsKey(playerId),
        false);
    multiplayerGameManager.endGame(gameId);
  }

  @Test
  public void endGame() {
    int gameId = multiplayerGameManager.addGame(new Game(new HashMap<>(), "Test name", 5, null,
        3));
    int gamesSize = multiplayerGameManager.getGames().size();
    multiplayerGameManager.endGame(gameId);
    assertTrue(multiplayerGameManager.getGames().get(gameId).isEnded());
  }

  @Test
  public void getPlayerPositions() {
    int gameId = multiplayerGameManager.addGame(new Game(new HashMap<>(), "Test name", 5, null,
        3));
    Vector2D position = new Vector2D(5, 5);
    int playerId = multiplayerGameManager.connectToGame(gameId, position, "Player");
    assertEquals(multiplayerGameManager.getPlayerPositions(gameId).contains(new Player("Player",
        playerId, position, carColour)), true);
  }

  @Test
  public void updatePlayerPosition() {
    int gameId = multiplayerGameManager.addGame(new Game(new HashMap<>(), "Test name", 5, null,
        3));
    Vector2D position = new Vector2D(5, 5);
    int playerId = multiplayerGameManager.connectToGame(gameId, position, "Player");

    Vector2D position2 = new Vector2D(3, 3);
    multiplayerGameManager.updatePlayerPosition(gameId, playerId, position2);

    assertEquals(multiplayerGameManager.getPlayerPositions(gameId).contains(new Player("Player",
        playerId, position2, carColour)), true);
  }

  @Before
  public void setUp() throws Exception {
    multiplayerGameManager = new MultiplayerGameManager();
  }
}