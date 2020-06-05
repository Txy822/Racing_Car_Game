package Server.Request;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import Server.MultiplayerGameManager;
import org.junit.Before;
import org.junit.Test;

public class EndGameRequestTest {

  private EndGameRequest request;
  private MultiplayerGameManager multiplayerGameManager;
  private int gameId;

  @Before
  public void setUp() throws Exception {
    multiplayerGameManager = new MultiplayerGameManager();
    gameId = multiplayerGameManager.addTestGame();
    request = new EndGameRequest(gameId);
    request.setMultiplayerGameManager(multiplayerGameManager);
  }

  @Test
  public void setMultiplayerGameManager() {
    assertEquals(request.getMultiplayerGameManager(), multiplayerGameManager);
  }

  @Test
  public void handleRequest() {
    request.handleRequest();

    assertTrue(multiplayerGameManager.getGameById(gameId).isEnded());
  }

  @Test
  public void create() {
  }
}