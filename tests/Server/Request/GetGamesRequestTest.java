package Server.Request;

import static org.junit.Assert.assertEquals;

import Server.MultiplayerGameManager;
import Server.Response.GetGamesResponse;
import org.junit.Before;
import org.junit.Test;

public class GetGamesRequestTest {

  private GetGamesRequest request;
  private MultiplayerGameManager multiplayerGameManager;
  private int gameId;

  @Before
  public void setUp() throws Exception {
    multiplayerGameManager = new MultiplayerGameManager();
    gameId = multiplayerGameManager.addTestGame();

    request = new GetGamesRequest();
    request.setMultiplayerGameManager(multiplayerGameManager);
  }

  @Test
  public void setMultiplayerGameManager() {
    assertEquals(request.getMultiplayerGameManager(), multiplayerGameManager);
  }

  @Test
  public void handleRequest() {
    GetGamesResponse response = (GetGamesResponse) request.handleRequest();
    assertEquals(response.getGames().get(0), multiplayerGameManager.getGames().get(gameId));
  }
}