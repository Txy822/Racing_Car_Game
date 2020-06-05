package Server.Request;

import static org.junit.Assert.assertEquals;

import Server.Game;
import Server.MultiplayerGameManager;
import Server.Player;
import Server.Response.GetPlayerPositionsResponse;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

public class GetPlayerPositionsRequestTest {

  private MultiplayerGameManager multiplayerGameManager;
  private GetPlayerPositionsRequest request;
  private HashMap<Integer, Player> players;
  private int gameId;

  @Test
  public void handleRequest() {
    assertEquals(((GetPlayerPositionsResponse) request.handleRequest()).getPositions().size(),
        players.size());
  }

  @Test
  public void setMultiplayerGameManager() {
    assertEquals(multiplayerGameManager, request.getMultiplayerGameManager());
  }

  @Before
  public void setUp() {
    players = new HashMap<>();
    multiplayerGameManager = new MultiplayerGameManager();
    gameId = multiplayerGameManager.addGame(new Game(players, "Test name", 5, null, 3));

    request = new GetPlayerPositionsRequest(gameId);
    request.setMultiplayerGameManager(multiplayerGameManager);
  }
}