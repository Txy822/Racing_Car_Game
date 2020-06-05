package Server.Request;

import static org.junit.Assert.assertFalse;

import Server.Game;
import Server.MultiplayerGameManager;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

public class DisconnectGameRequestTest {

  private MultiplayerGameManager multiplayerGameManager;
  private DisconnectGameRequest request;

  private int gameId;
  private int playerId;

  @Test
  public void handleRequest() {
    request.handleRequest();
    assertFalse(multiplayerGameManager.getGames().get(gameId).hasPlayer(playerId));
  }

  @Test
  public void setMultiplayerGameManager() {
  }

  @Before
  public void setUp() {
    multiplayerGameManager = new MultiplayerGameManager();
    gameId = multiplayerGameManager.addGame(new Game(new HashMap<>(), "Test name", 5, null, 3));
    playerId = multiplayerGameManager.connectToGame(gameId, null, "Player");

    request = new DisconnectGameRequest(gameId, playerId);
    request.setMultiplayerGameManager(multiplayerGameManager);
  }
}