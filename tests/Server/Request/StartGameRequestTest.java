package Server.Request;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import Server.MultiplayerGameManager;
import org.junit.Before;
import org.junit.Test;

public class StartGameRequestTest {

  private StartGameRequest request;
  private MultiplayerGameManager multiplayerGameManager;
  private int gameId;

  @Before
  public void setUp() throws Exception {
    multiplayerGameManager = new MultiplayerGameManager();
    gameId = multiplayerGameManager.addTestGame();

    request = new StartGameRequest(gameId);
    request.setMultiplayerGameManager(multiplayerGameManager);
  }

  @Test
  public void handleRequest() {
    request.handleRequest();

    assertTrue(multiplayerGameManager.getGames().get(gameId).isStarted());
  }

  @Test
  public void setMultiplayerGameManager() {
    assertEquals(request.getMultiplayerGameManager(), multiplayerGameManager);
  }
}