package Server.Response;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CreateGameResponseTest {

  private CreateGameResponse response;

  @Test
  public void getCode() {
    assertEquals(response.getCode(), 0);
  }

  @Test
  public void getGameId() {
    assertEquals(response.getGameId(), 1);
  }

  @Before
  public void setUp() {
    response = new CreateGameResponse(0, 1);
  }
}