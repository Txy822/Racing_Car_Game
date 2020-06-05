package Server.Request;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import Server.MultiplayerGameManager;
import org.junit.Before;
import org.junit.Test;

public class CreateGameRequestTest {

  private CreateGameRequest request;
  private MultiplayerGameManager multiplayerGameManager;

  @Test
  public void handleRequest() {
    request.handleRequest();
    assertEquals(1, multiplayerGameManager.getGames().size());
  }

  @Test
  public void setMultiplayerGameManager() {
    assertEquals(request.getMultiplayerGameManager(), multiplayerGameManager);
  }

  @Before
  public void setUp() {
    multiplayerGameManager = new MultiplayerGameManager();
    request = new CreateGameRequest();
    request.setMultiplayerGameManager(multiplayerGameManager);

    CreateGameRequest request = new CreateGameRequest("Test", 5, null, 3);
    assertNotNull(request);
  }

  @Test
  public void setGameName() {
    request.setGameName("Test");
    assertEquals(request.getGameName(), "Test");
  }
}