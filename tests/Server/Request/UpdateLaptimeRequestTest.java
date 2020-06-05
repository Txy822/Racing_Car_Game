package Server.Request;

import static org.junit.Assert.assertEquals;

import Server.MultiplayerGameManager;
import org.junit.Before;
import org.junit.Test;

public class UpdateLaptimeRequestTest {

  private UpdateLaptimeRequest request;
  private MultiplayerGameManager multiplayerGameManager;
  private int gameId;
  private int playerId;
  private int time = 100;

  @Before
  public void setUp() throws Exception {
    multiplayerGameManager = new MultiplayerGameManager();
    gameId = multiplayerGameManager.addTestGame();
    playerId = multiplayerGameManager.connectToGame(gameId, null, null);

    request = new UpdateLaptimeRequest(playerId, gameId, time);
    request.setMultiplayerGameManager(multiplayerGameManager);
  }

  @Test
  public void setMultiplayerGameManager() {
    assertEquals(request.getMultiplayerGameManager(), multiplayerGameManager);
  }

  @Test
  public void handleRequest() {
    request.handleRequest();

    assertEquals(
        multiplayerGameManager.getGames().get(gameId).getPlayers().get(playerId).getLastLaptime(),
        time);

  }
}