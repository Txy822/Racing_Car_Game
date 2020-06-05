package Server.Request;

import static org.junit.Assert.assertEquals;

import Server.Game;
import Server.MultiplayerGameManager;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

public class JoinGameRequestTest {

  private JoinGameRequest request;
  private MultiplayerGameManager multiplayerGameManager;
  private int gameId;
  private int originalSize;

  @Test
  public void handleRequest() {
    request.handleRequest();
    assertEquals(multiplayerGameManager.getGames().get(gameId).getPlayers().size(),
        originalSize + 1);
  }

  @Test
  public void setMultiplayerGameManager() {
    assertEquals(multiplayerGameManager, request.getMultiplayerGameManager());
  }

  @Before
  public void setUp() {
    multiplayerGameManager = new MultiplayerGameManager();
    gameId = multiplayerGameManager.addGame(new Game(new HashMap<>(), "Test name", 5, null, 3));
    originalSize = multiplayerGameManager.getGames().get(gameId).getPlayers().size();
    request = new JoinGameRequest(gameId, null, "Player");
    request.setMultiplayerGameManager(multiplayerGameManager);
  }
}