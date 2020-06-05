package Server.Request;

import static org.junit.Assert.assertEquals;

import Server.Game;
import Server.MultiplayerGameManager;
import Server.Response.GameResponse;
import org.junit.Before;
import org.junit.Test;

public class GetGameByIdRequestTest {

  private GetGameByIdRequest request;
  private MultiplayerGameManager multiplayerGameManager;
  private int gameId;

  @Before
  public void setUp() throws Exception {
    multiplayerGameManager = new MultiplayerGameManager();
    gameId = multiplayerGameManager.addTestGame();

    request = new GetGameByIdRequest(gameId);
    request.setMultiplayerGameManager(multiplayerGameManager);
  }

  @Test
  public void handleRequest() {
    Game game = ((GameResponse) request.handleRequest()).getGame();

    assertEquals(game.getId(), multiplayerGameManager.getGameById(gameId).getId());
  }

  @Test
  public void setMultiplayerGameManager() {
    assertEquals(request.getMultiplayerGameManager(), multiplayerGameManager);
  }
}