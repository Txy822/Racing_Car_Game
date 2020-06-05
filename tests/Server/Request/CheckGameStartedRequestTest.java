package Server.Request;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import Server.Game;
import Server.MultiplayerGameManager;
import Server.Response.BooleanResponse;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

public class CheckGameStartedRequestTest {

  private CheckGameStartedRequest request;
  private MultiplayerGameManager multiplayerGameManager;
  private int gameId;

  @Before
  public void setUp() throws Exception {
    multiplayerGameManager = new MultiplayerGameManager();
    gameId = multiplayerGameManager.addGame(new Game(new HashMap<>(), "Theo's game!", 5, null,
        3));

    request = new CheckGameStartedRequest(gameId);
    request.setMultiplayerGameManager(multiplayerGameManager);
  }

  @Test
  public void setMultiplayerGameManager() {

    assertEquals(request.getMultiplayerGameManager(), multiplayerGameManager);
  }

  @Test
  public void handleRequest() {
    BooleanResponse responseA = (BooleanResponse) request.handleRequest();
    assertFalse(responseA.isBool());

    multiplayerGameManager.startGame(gameId);
    BooleanResponse responseB = (BooleanResponse) request.handleRequest();

    assertTrue(responseB.isBool());
  }
}