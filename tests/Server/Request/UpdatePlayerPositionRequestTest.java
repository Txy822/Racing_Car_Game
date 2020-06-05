package Server.Request;

import static org.junit.Assert.assertEquals;

import Physics.Vector2D;
import Server.MultiplayerGameManager;
import org.junit.Before;
import org.junit.Test;

public class UpdatePlayerPositionRequestTest {

  private UpdatePlayerPositionRequest request;
  private MultiplayerGameManager multiplayerGameManager;
  private int gameId;
  private int playerId;
  private Vector2D position;

  @Test
  public void handleRequest() {
    request.handleRequest();

    assertEquals(
        multiplayerGameManager.getGames().get(gameId).getPlayers().get(playerId).getVector(),
        position);
  }

  @Test
  public void setMultiplayerGameManager() {
    assertEquals(request.getMultiplayerGameManager(), multiplayerGameManager);
  }

  @Before
  public void setUp() {
    multiplayerGameManager = new MultiplayerGameManager();
    gameId = multiplayerGameManager.addTestGame();
    playerId = multiplayerGameManager.connectToGame(gameId, null, null);

    position = new Vector2D(5, 5);

    request = new UpdatePlayerPositionRequest(gameId, playerId, position);
    request.setMultiplayerGameManager(multiplayerGameManager);
  }
}