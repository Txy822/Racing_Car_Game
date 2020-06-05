package Server.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import Server.Game;
import org.junit.Before;
import org.junit.Test;

public class GameResponseTest {

  private GameResponse response;
  private Game game;

  @Before
  public void setUp() throws Exception {
    game = new Game(null, null, 0, null, 0);
    response = new GameResponse(game);
  }

  @Test
  public void getCode() {
    assertEquals(response.getCode(), 0);
  }

  @Test
  public void getGame() {
    response.setGame(game);
    assertEquals(response.getGame(), game);

  }

  @Test
  public void setGame() {
    response.setGame(null);
    assertNull(response.getGame());
  }
}