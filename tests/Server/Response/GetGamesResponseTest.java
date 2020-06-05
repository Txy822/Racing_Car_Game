package Server.Response;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class GetGamesResponseTest {

  private GetGamesResponse response;

  @Before
  public void setUp() throws Exception {
    response = new GetGamesResponse(0, null);
  }

  @Test
  public void getCode() {
    assertEquals(response.getCode(), 0);
  }

  @Test
  public void getGames() {
  }
}